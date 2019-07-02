package com.lc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lc.demo.entity.Hr;
import com.lc.demo.entity.ResultCode;
import com.lc.demo.entity.ResultJson;
import com.lc.demo.mapper.HrMapper;
import com.lc.demo.model.User;
import com.lc.demo.service.AuthService;

@RestController
public class HrController {
	
	@Autowired
	private HrMapper hrMapper;
	
	@Autowired
	private AuthService authService;
	
	
	@RequestMapping(path = "/auth/login", method = RequestMethod.POST)
	public ResultJson<?> login(@RequestBody User user) {
		JSONObject jsonObject = authService.login(user.getUsername(), user.getPassword());
		return ResultJson.ok(jsonObject);
	}
	
	@RequestMapping(path = "/auth/register", method = RequestMethod.POST)
	public ResultJson<?> register(@RequestBody User user) {
		Hr userDetail = new Hr(user.getUsername(), user.getPassword());
		if(authService.register(userDetail)) {
			return ResultJson.ok();
		} else {
			return ResultJson.failure(ResultCode.SERVER_ERROR);
		}
	}
	
	@RequestMapping(path = "/api/getUserInfo/{id}", method = RequestMethod.GET)
	public ResultJson<?> getEmployeeInfo(@PathVariable(name = "id") Integer id) {
		Hr hr = hrMapper.selectByPrimaryKey(id);
		return ResultJson.ok(hr);
	}
	

}
