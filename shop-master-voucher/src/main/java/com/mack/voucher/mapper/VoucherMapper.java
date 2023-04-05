package com.mack.voucher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mack.common.entity.Voucher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mack
 * @since 2022-11-22
 */
public interface VoucherMapper extends BaseMapper<Voucher> {

    List<Voucher> queryVoucherOfShop(@Param("shopId") Long shopId);
}
