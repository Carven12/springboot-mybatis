package com.lc.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lc.demo.entity.Hr;
import com.lc.demo.entity.ResultCode;
import com.lc.demo.entity.ResultJson;
import com.lc.demo.exception.CustomException;
import com.lc.demo.mapper.HrMapper;
import com.lc.demo.service.AuthService;
import com.lc.demo.utils.JwtTokenUtil;

/**
 * 用户注册登录接口的实现
 * @author liangc
 * 2019年7月1日
 */
@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	HrMapper hrMapper;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Boolean register(Hr userDetail) {
		String username = userDetail.getUsername();
		if(hrMapper.findHrByUserName(username) != null) {
			//throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户已存在"));
			return false;
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

	/**
	 * 用户登录逻辑
	 */
	@Override
	public JSONObject login(String username, String password) {
		//用户验证
        Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        Hr userDetail = (Hr) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetail);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("user", userDetail);
		return jsonObject;
	}

	@Override
	public void logout(String token) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 用户验证
	 * @param username
	 * @param password
	 * @return
	 */
	private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }

}
