package com.mack.client.service;

import com.mack.client.config.FeignConfig;
import com.mack.common.dto.Result;
import com.mack.common.entity.VoucherOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "cloud-voucher-service", configuration = FeignConfig.class)
public interface VoucherOrderFeignService {

    @PostMapping("/voucher-order-service/seckill/{id}")
    Result seckillVoucher(@PathVariable("id") Long voucherId);

    @PostMapping("/voucher-order-service/seckill/{msg}/{voucherId}")
    void createVoucherOrder(@PathVariable("msg") String msg, @PathVariable("voucherId") Long voucherId);
}
