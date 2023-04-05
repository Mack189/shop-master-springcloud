package com.mack.login.service.impl;

import com.mack.common.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mack.login.mapper.UserInfoMapper;
import com.mack.login.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mack
 * @since 2022-11-22
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
