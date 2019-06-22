package com.lc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lc.demo.entity.Hr;
import com.lc.demo.mapper.HrMapper;

@RestController
public class HrController {
	
	@Autowired
	private HrMapper hrMapper;
	
	@RequestMapping("/getHrInfo")
	public Hr getHrInfo() {
		Hr hr = hrMapper.getUserById(3);
		return hr;
	}

}
