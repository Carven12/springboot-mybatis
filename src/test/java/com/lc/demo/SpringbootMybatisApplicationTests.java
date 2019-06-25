package com.lc.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lc.demo.entity.Hr;
import com.lc.demo.mapper.HrMapper;
import com.lc.demo.utils.JwtTokenUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {
	
	@Autowired
	HrMapper hrMapper;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Test
	public void contextLoads() {
		Hr hr = hrMapper.selectByPrimaryKey(3);
		String tokenString = jwtTokenUtil.generateToken(hr);
		Logger logger = LoggerFactory.getLogger(getClass());
		logger.info(tokenString);
	}

}
