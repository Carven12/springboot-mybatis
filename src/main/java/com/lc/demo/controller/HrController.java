package com.lc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lc.demo.entity.Hr;
import com.lc.demo.entity.RespModel;
import com.lc.demo.model.User;
import com.lc.demo.service.AuthService;

@RestController
public class HrController {
	
//	@Autowired
//	private HrMapper hrMapper;
//	
//	@Autowired
//	private EmployeeMapper employeeMapper;
//	
//	@RequestMapping(path = "/getEmployeeInfo/{id}", method = RequestMethod.GET)
//	public Employee getEmployeeInfo(@PathVariable(name = "id") Integer id) {
//		Employee employee = employeeMapper.selectByPrimaryKey(id);
//		return employee;
//	}
	
	@Autowired
	private AuthService authService;
	
	
	@RequestMapping(path = "/auth/login", method = RequestMethod.POST)
	public RespModel login(@RequestBody JSONObject json) {
//		String username = json.getString("username");
//		String password = json.getString("password");
		RespModel respModel = new RespModel(200, "", json);
		return respModel;
	}
	
	@RequestMapping(path = "/auth/register", method = RequestMethod.POST)
	public RespModel register(@RequestBody User user) {
		Hr userDetail = new Hr(user.getUsername(), user.getPassword());
		if(authService.register(userDetail)) {
			RespModel respModel = new RespModel(200, "success!");
			return respModel;
		} else {
			RespModel respModel = new RespModel(500, "ERROR!");
			return respModel;
		}
	}
	

}
