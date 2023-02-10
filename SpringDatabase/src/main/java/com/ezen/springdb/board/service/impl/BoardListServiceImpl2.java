package com.ezen.springdb.board.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springdb.board.mapper.ServiceBoardMapper;
import com.ezen.springdb.board.service.BoardListService;
import com.ezen.springdb.dto.BoardDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BoardListServiceImpl2 implements BoardListService{

	@Autowired
	ServiceBoardMapper mapper;
	
	@Override
		public BoardDTO clickTitle(Integer board_id, 
				HttpServletRequest request, HttpServletResponse response) {
			
			// 쿠키는 사용하되 json은 안쓰는 방법
			Cookie[] cookies = request.getCookies();
			String[] splited = null;
			boolean viewed = false;
			Cookie viewCookie = null;
			
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie != null && cookie.getName().equals("viewed")) {
						// 같은 이름의 cookie를 찾았다면 viewCookie에 넣어준다
						viewCookie = cookie;
						
						// "33/3456/44/11/55.." 쿠키 값은 4096 bytes 한계가 있다
						String ids = cookie.getValue();
						
						// split한 배열을 리스트로 만들어 주기
						splited = ids.split("/");
						List<String> idList = Arrays.asList(splited);
						viewed = idList.contains(board_id.toString());
					}
				}
			}
			
			// 본적이 없는 경우
			if (!viewed) {
				// 조회수 증가
				mapper.plusView(board_id);
				if (splited == null) {
					// 본적도 없고 쿠키도 없는 경우
					// 새로운 쿠키를 만들어서 준다
					viewCookie = new Cookie("viewed", board_id.toString());
				} else {
					// 쿠키는 있지만 이 글을 처음보는 경우 (맨 뒤에 새 id를 추가)
					viewCookie.setValue(viewCookie.getValue() + "/" + board_id);
				}
				viewCookie.setPath(request.getContextPath() + "/service/board");
				response.addCookie(viewCookie);
				
			}
		
			
			return mapper.get(board_id);
		}
}
