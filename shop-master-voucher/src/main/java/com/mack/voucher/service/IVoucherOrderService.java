package com.mack.voucher.service;

import com.mack.common.dto.Result;
import com.mack.common.entity.VoucherOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mack
 * @since 2022-11-22
 */
public interface IVoucherOrderService extends IService<VoucherOrder> {

    Result seckillVoucher(Long voucherId);

    void createVoucherOrder(String msg, Long voucherId);
}
