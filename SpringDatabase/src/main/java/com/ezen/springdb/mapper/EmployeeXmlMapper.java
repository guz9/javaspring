package com.ezen.springdb.mapper;

import java.util.List;

import com.ezen.springdb.dto.Employee;

public interface EmployeeXmlMapper {
	
	public List<Employee> getAll();

	public Employee get(Integer employee_id);
	
	// 매개변수 Integer salary보다 월급을 적게 받는 사람들을 구하고 싶을 때 설정
	public List<Employee> getLessSalary(Integer salary);
	
	public Integer getLastId();
	
	public Integer insert(Employee emp);
}
