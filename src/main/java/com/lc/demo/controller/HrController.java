package com.lc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lc.demo.entity.Employee;
import com.lc.demo.entity.Hr;
import com.lc.demo.mapper.EmployeeMapper;
import com.lc.demo.mapper.HrMapper;

@RestController
public class HrController {
	
	@Autowired
	private HrMapper hrMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;

	
	@RequestMapping("/getHrInfo")
	public Hr getHrInfo() {
		Hr hr = hrMapper.getUserById(3);
		return hr;
	}
	
	@RequestMapping("/getEmployeeInfo/{id}")
	public Employee getEmployeeInfo(@PathVariable(name = "id") Integer id) {
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}
	

}
