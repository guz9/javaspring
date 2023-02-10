package com.ezen.springdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.springdb.dto.BoardDTO;
import com.ezen.springdb.dto.Employee;
import com.ezen.springdb.mapper.BoardMapper;
import com.ezen.springdb.mapper.EmployeeMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/test") // test라는 주소로 들어오면 이 컨트롤러를 실행하겠다
@Controller
public class DatabaseTestController {

	// 이렇게하면 hikari 소스가 알아서 들어옴
	@Autowired
	DataSource ds;
	
	@Autowired
	EmployeeMapper mapper;
	
	@Autowired
	BoardMapper board_mapper;
	
	@GetMapping(value = {"/index", "/"})
	public String index() {
		
		return "test/index";
	}
	
	@GetMapping("/employees")
	public void employees(Model model) {
		String sql = "SELECT * FROM employees";
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		){
			List<Employee> employees = new ArrayList<>();
			
//			while (rs.next()) {
//				employees.add(new Employee(
//							rs.getInt("employee_id"),
//							rs.getString("first_name"),
//							rs.getString("last_name"),
//							rs.getInt("department_id"),
//							rs.getString("job_id"),
//							rs.getInt("salary")
//						));
//			}
			
			model.addAttribute("employees", employees);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// Mybatis로 여러줄 SELECET (알아서 List<DTO>타입으로 변환해서 가져옴)
	@GetMapping("/employees2")
	public String employees2(Model model) {
		List<Employee> employees = mapper.getList();
		
		log.info(employees);
		
		model.addAttribute("employees", employees);
		
		return "/test/employees";
	}
	
	// Mybatis로 하나만 SELECET (알아서 DTO타입으로 가져옴)
	@GetMapping("/board/get")
	public String getBoard(Model model, Integer board_id) {
		model.addAttribute("board", board_mapper.get(board_id));
		
		return "test/board_detail";
	}
	
	@GetMapping("/board/add")
	public String addBoard(Model model, BoardDTO board) {
		int row = board_mapper.add(board);
		
		return "redirect:/test/";
	}
	
	@GetMapping("/board/list")
	public String boardList(Model model) {
		model.addAttribute("boards", board_mapper.getAll());
		
		return "/test/board_list";
	}
	
	@GetMapping("/board/modify")
	public String modifyBoard(BoardDTO board) {
		board_mapper.modify(board);
		
		return "redirect:/test/board/list";
	}
	
	@GetMapping("/board/delete")
	public String deleteBoard(Integer board_id) {
		board_mapper.delete(board_id);
		
		return "redirect:/test/board/list";
	}
	
	
	
	
	
}
