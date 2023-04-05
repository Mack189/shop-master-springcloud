package com.mack.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mack.common.dto.LoginFormDTO;
import com.mack.common.dto.Result;
import com.mack.common.entity.User;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mack
 * @since 2022-11-22
 */
public interface IUserService extends IService<User> {

    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result sign();

    Result signCount();
}
