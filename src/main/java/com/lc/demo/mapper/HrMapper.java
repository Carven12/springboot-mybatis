package com.lc.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lc.demo.entity.Hr;

@Mapper //加上该注解才能使用@MapperScan扫描到
public interface HrMapper {

	Hr getUserById(@Param("id") int id);
	 
    int updateUser(@Param("hr") Hr hr);
 
    int insertUser(@Param("hr") Hr hr);
 
    int deleteUserById(@Param("id") int id);
}
