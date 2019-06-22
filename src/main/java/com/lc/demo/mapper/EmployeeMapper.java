package com.lc.demo.mapper;

import com.lc.demo.entity.Employee;

public interface EmployeeMapper {
    int insert(Employee record);

    int insertSelective(Employee record);
}