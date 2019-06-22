package com.lc.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lc.demo.entity.Employee;
import com.lc.demo.mapper.EmployeeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	
	
	
	@Test
	public void contextLoads() {
		Employee employee = employeeMapper.selectByPrimaryKey(1);
		System.out.print(employee.toString());
	}

}
