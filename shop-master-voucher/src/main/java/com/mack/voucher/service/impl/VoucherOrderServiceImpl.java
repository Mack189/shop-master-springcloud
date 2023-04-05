package com.mack.voucher.service.impl;

import cn.hutool.json.JSONUtil;
import com.mack.common.dto.Result;
import com.mack.common.entity.VoucherOrder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mack.common.utils.RedisIdWorker;
import com.mack.common.utils.UserHolder;
import com.mack.voucher.mapper.VoucherOrderMapper;
import com.mack.voucher.service.ISeckillVoucherService;
import com.mack.voucher.service.IVoucherOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mack
 * @since 2022-11-22
 */
@Slf4j
@Service
@Transactional
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {

    @Resource
    private ISeckillVoucherService seckillVoucherService;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RabbitTemplate rabbitTemplate;

    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;

    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("seckill.lua"));
        SECKILL_SCRIPT.setResultType(Long.class);
    }

    /**
     * 秒杀下单
     * @param voucherId
     * @return
     */
    @Override
    public Result seckillVoucher(Long voucherId) {
        //获取用户
        Long userId = UserHolder.getUser().getId();
        //获取订单
        Long orderId = redisIdWorker.nextId("order");
        //1.执行lua脚本
        Long result = stringRedisTemplate.execute(SECKILL_SCRIPT, Collections.emptyList(),
                voucherId.toString(), userId.toString(), String.valueOf(orderId));

        //2.判断结果是否为0
        int value = result.intValue();
        if(value!=0){
            return Result.fail(value==1?"库存不足":"不能重复下单");
        }
        //2.2为零 有购买资格，把下单信息保存到阻塞队列
        //.创建订单
        VoucherOrder voucherOrder = new VoucherOrder();
        //.1订单id 用id全局唯一生成器
        voucherOrder.setId(orderId);
        //.2用户id
        voucherOrder.setUserId(userId);
        //.3代金卷id
        voucherOrder.setVoucherId(voucherId);

        //放入mq
        String jsonStr = JSONUtil.toJsonStr(voucherOrder);
        rabbitTemplate.convertAndSend("X","XA",jsonStr);

        //返回id
        Long Id = redisIdWorker.nextId("order");
        return Result.ok(Id);

    }

    @Override
    public void createVoucherOrder(String msg, Long voucherId) {
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!?????????????????????????");
        VoucherOrder voucherOrder = JSONUtil.toBean(msg, VoucherOrder.class);
        save(voucherOrder);
        boolean success = seckillVoucherService.update().setSql("stock = stock - 1")
                .eq("voucher_id", voucherId).gt("stock", 0).update();
        if (!success) {
            //扣减失败
            log.error("库存不足");
            return;
        }
        log.info("扣减成功！！");
    }

}
