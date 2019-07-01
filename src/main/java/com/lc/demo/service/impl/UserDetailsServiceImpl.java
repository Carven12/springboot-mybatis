package com.lc.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lc.demo.entity.Hr;
import com.lc.demo.mapper.HrMapper;
import com.lc.demo.utils.JwtTokenUtil;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	HrMapper hrMapper;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Hr hr = hrMapper.findHrByUserName(s);
		if (null == hr) {
			throw new UsernameNotFoundException(String.format("未找到名字为'%s'.", s));
		}
		return hr;
	}

}
