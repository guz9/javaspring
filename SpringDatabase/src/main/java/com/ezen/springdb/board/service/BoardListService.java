package com.ezen.springdb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.springdb.dto.BoardDTO;

public interface BoardListService {
	
	// 해당 글을 읽는 서비스 설정
	BoardDTO clickTitle(Integer board_id, HttpServletRequest request,
			HttpServletResponse response);
	
	// 작성자를 눌렀을 때 (회원정보가 뜨게하기)
//	Member clickWriterName();

}
