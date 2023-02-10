package com.ezen.springdb.board.contraller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.springdb.board.service.BoardListService;

@RequestMapping("/service/board/list")
@Controller
public class BoardListController {

	@Autowired
	BoardListService service;
	
	@GetMapping("/title")
	public String clickTitle(Model model,Integer board_id, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("board", service.clickTitle(board_id, request, response));
		
		
		return "test/board_detail";
	}
	
}
