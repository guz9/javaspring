package com.ezen.springdb.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor

// @NonNull이 붙어있는 생성자를 만들기 위해 해야하는 롬복 설정
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
	private Integer employee_id;
	private String first_name;
	@NonNull
	private String last_name;
	@NonNull
	private String email;
	private String phone_number;
	@NonNull
	private Date hire_date;
	@NonNull
	private String job_id;
	private Integer salary;
	private Double commission_pct;
	private Integer manager_id;
	private Integer department_id;
	

}
