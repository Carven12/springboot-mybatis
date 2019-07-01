package com.lc.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.lc.demo.entity.Hr;

/**
 * 用户登录注册相关接口
 * @author liangc
 * 2019年7月1日
 */
public interface AuthService {
	/**
     * 注册用户
     * @param userDetail
     * @return Boolean
     */
    Boolean register(Hr userDetail);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    JSONObject login(String username, String password);

    /**
     * 登出
     * @param token
     */
    void logout(String token);
}
