package com.mack.voucher.service.impl;

import com.mack.common.entity.SeckillVoucher;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mack.voucher.mapper.SeckillVoucherMapper;
import com.mack.voucher.service.ISeckillVoucherService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 秒杀优惠券表，与优惠券是一对一关系 服务实现类
 * </p>
 *
 * @author Mack
 * @since 2022-11-22
 */
@Service
public class SeckillVoucherServiceImpl extends ServiceImpl<SeckillVoucherMapper, SeckillVoucher> implements ISeckillVoucherService {

}
