package com.mack.store.service.impl;

import com.mack.common.entity.ShopType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mack.store.mapper.ShopTypeMapper;
import com.mack.store.service.IShopTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mack
 * @since 2022-11-22
 */
@Service("IShopTypeService")
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

}
