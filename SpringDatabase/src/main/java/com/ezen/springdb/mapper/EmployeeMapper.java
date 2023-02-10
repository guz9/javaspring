package com.ezen.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ezen.springdb.dto.Employee;

public interface EmployeeMapper {
	
	// 이런 mapper를 만들어달라고 mtbitise에게 부탁
	@Select("SELECT * FROM employees")
	public List<Employee> getList();
	
}
