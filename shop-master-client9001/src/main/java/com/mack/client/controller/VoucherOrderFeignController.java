package com.mack.client.controller;

import cn.hutool.json.JSONUtil;
import com.mack.client.service.VoucherOrderFeignService;
import com.mack.common.dto.Result;
import com.mack.common.entity.VoucherOrder;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/voucher-order")
public class VoucherOrderFeignController {

    @Resource
    private VoucherOrderFeignService voucherOrderFeignService;


    @PostMapping("seckill/{id}")
    public Result seckillVoucher(@PathVariable("id") Long voucherId) {
        return voucherOrderFeignService.seckillVoucher(voucherId);
    }

    /**
     * sheng  消费者1
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(queues = "QA")
    public void receivedA(Message message, Channel channel)throws Exception{

        try {
            String msg=new String(message.getBody());
            log.info("正常队列:");
            VoucherOrder voucherOrder = JSONUtil.toBean(msg, VoucherOrder.class);
            log.info(voucherOrder.toString());
            //数据库秒杀库存减一
            Long voucherId=voucherOrder.getVoucherId();
            voucherOrderFeignService.createVoucherOrder(msg, voucherId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.basicAck((Long) message.getMessageProperties().getDeliveryTag(),false);
        }

    }

    /**
     * sheng  消费者2
     * @param message
     * @throws Exception
     */
    @RabbitListener(queues = "QD")
    public void receivedD(Message message, Channel channel)throws Exception{
        try {
            log.info("死信队列:");
            String msg=new String(message.getBody());
            VoucherOrder voucherOrder = JSONUtil.toBean(msg, VoucherOrder.class);
            log.info(voucherOrder.toString());

            Long voucherId=voucherOrder.getVoucherId();
            voucherOrderFeignService.createVoucherOrder(msg, voucherId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.basicAck((Long) message.getMessageProperties().getDeliveryTag(),false);
        }

    }

}
