package com.lc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lc.demo.entity.Hr;
import com.lc.demo.entity.RespModel;

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
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public RespModel login(@RequestBody JSONObject json) {
		String username = json.getString("username");
		String password = json.getString("password");
		return null;
	}
	

}
