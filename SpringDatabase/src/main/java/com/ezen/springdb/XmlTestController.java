package com.ezen.springdb;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.springdb.dto.Employee;
import com.ezen.springdb.mapper.EmployeeXmlMapper;


import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/xml")
@Controller
public class XmlTestController {

	@Autowired
	EmployeeXmlMapper emp_mapper;
	
	@GetMapping("/employee/list")
	public String employeeList(Model model) {
		model.addAttribute("employees", emp_mapper.getAll());
		
		return "xml/emp_list";
	}
	
	// int는 null을 구분하기 힘들기 때문에 Integer로 설정한다
	@GetMapping("/employee/get")
	public String getEmployee(Model model, Integer employee_id) {
		
		if(employee_id != null) {
			model.addAttribute("employee", emp_mapper.get(employee_id));
		}
		
		return "xml/emp";
	}
	
	@GetMapping("/employee/where")
	public String getWhere(Model model) {
		model.addAttribute("employees", emp_mapper.getLessSalary(8000));
		
		return "xml/emp_list";
	}
	
	@GetMapping("/employee/insert")
	public String insert(Model model) {
		// 한 컨트롤러에서 여러가지 DAO의 접근과 동시에 여러가지 서비스(비지니스 로직) 역시 가능하게 한다
		// 그렇기에 계층간의 분리가 필요하다
		
		// 이메일이 겹치는 불상사를 막기 위해 insert와 동시에
		// employee_id의 최대값(신규값)을 가져오게 하여 "EMAIL" + last_id로 설정
		Integer last_id = emp_mapper.getLastId();
		
		Employee e = new Employee("Test", "EMAIL" + last_id, new Date(), "IT_PROG");
		
		log.info("before insert(employee_id가 비어있음): " + e);
		
		Integer row = emp_mapper.insert(e);
		
		log.info(row + "행이 업데이트 되었습니다...");
		
		log.info("after insert(employee_id가 채워져있음, <selectKey>의 기능): " + e);
		
		// 받아온 id로 다시한번 select해서 모델에 실어놓음
		model.addAttribute("employee", emp_mapper.get(e.getEmployee_id()));
		
		return "xml/emp";
	}
}
