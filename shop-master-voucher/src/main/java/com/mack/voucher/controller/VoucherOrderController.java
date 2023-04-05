package com.mack.voucher.controller;


import com.mack.common.dto.Result;
import com.mack.common.entity.VoucherOrder;
import com.mack.voucher.service.IVoucherOrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mack
 * @since 2022-11-22
 */
@RestController
@RequestMapping("/voucher-order-service")
public class VoucherOrderController {

    @Resource
    private IVoucherOrderService voucherOrderService;

    @PostMapping("seckill/{id}")
    public Result seckillVoucher(@PathVariable("id") Long voucherId) {
        return voucherOrderService.seckillVoucher(voucherId);
    }

    @PostMapping("seckill/{msg}/{voucherId}")
    public void createVoucherOrder(@PathVariable("msg") String msg, @PathVariable("voucherId") Long voucherId){
        voucherOrderService.createVoucherOrder(msg, voucherId);
    }

}
