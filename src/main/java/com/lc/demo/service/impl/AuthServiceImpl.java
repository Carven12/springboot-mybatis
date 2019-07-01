package com.lc.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lc.demo.entity.Hr;
import com.lc.demo.mapper.HrMapper;
import com.lc.demo.service.AuthService;

/**
 * 用户注册登录接口的实现
 * @author liangc
 * 2019年7月1日
 */
@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	HrMapper hrMapper;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Boolean register(Hr userDetail) {
		String username = userDetail.getUsername();
		if(hrMapper.findHrByUserName(username) != null) {
			// throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户已存在"));
			logger.error("用户已存在");
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = userDetail.getPassword();
		userDetail.setPassword(encoder.encode(password));
		// 数据库操作异常暂未处理
		int count = 0;
		try {
			count = hrMapper.insert(userDetail);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(count != 0) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public JSONObject login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(String token) {
		// TODO Auto-generated method stub

	}

}
