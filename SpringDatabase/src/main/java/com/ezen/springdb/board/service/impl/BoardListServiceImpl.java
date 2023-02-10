package com.ezen.springdb.board.service.impl;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springdb.board.service.BoardListService;
import com.ezen.springdb.dto.BoardDTO;

import lombok.extern.log4j.Log4j2;

import com.ezen.springdb.board.mapper.ServiceBoardMapper;

@Log4j2
public class BoardListServiceImpl implements BoardListService{

	@Autowired
	ServiceBoardMapper board_mapper;
	
	
	@Override
	public BoardDTO clickTitle(Integer board_id, HttpServletRequest request, HttpServletResponse response) {
		
		// 글의 상세 내용을 가져온다 (DAO - 영속 계층)
		BoardDTO board = board_mapper.get(board_id);
		
		// 최근에 조회한 적이 있는지 검사한 후 (쿠키 검사, 추가 또는 갱신)
		Cookie[] cookies = request.getCookies();
		
		boolean viewed = false;
		
		JSONObject obj = null;
		
		// 반복문 자체도 null이 아닐 때 들어가야 한다고 설정
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// 쿠키가 null이 아닐때만 실행가능하게 설정해야 함
				if(cookie != null && cookie.getName().equals("viewed")) {
					
					// 쿠키에서 값을 꺼내면 String타입의 json이 나온다(= "ids":{1,2,3,4,} 이렇게 생긴 문자열) 
					String jsonString = cookie.getValue();
					
					// 문자열으 JSONObject 타입으로 파싱하기 위한 객체
					JSONParser parser = new JSONParser();
					
					try {
						// 파싱을 실행
						// JSONObject 로 다운캐스팅 필요
						obj = (JSONObject)(parser.parse(jsonString));
						
						// 값으로 꺼낸것이 자바스크립트 배열이기 때문에 JSONArray로 받음
						// ids라는 키 값에 넣어놓은 것이 배열이기 때문에 배열로 꺼내주면 된다
						JSONArray ids = (JSONArray) obj.get("ids");
						
						// 본적이 있다
						viewed = ids.contains(board_id);
						
						
						// 하나씩 반복을 돌릴 수도 있다(하지만 contains가 구현이 되어 있다면 contains가 더 깔끔하다)
	//					for (int i = 0; i < ids.size(); ++i) {
	//						ids.get(i);
	//					}
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		// 본적이 없는 경우
		if (!viewed) {
			
			// 해당 글의 조회수를 1 올린다 (영속 계층)
			board_mapper.plusView(board_id);
			
				// 조회한 적도 없고 쿠키도 아예 없는 경우
				if (obj == null) {
				// 자바스크립트 오브젝트(Map타입)처럼 사용할 수 있다
				
				// 내가 봤던 글 번호를 넘긴다 (Json 형태로)
				// const json = { ids: [board_id]}
				JSONObject json = new JSONObject();
				JSONArray arr = new JSONArray();
				
				arr.add(board_id);			
				json.put("ids", arr);
				
				log.info(json.toJSONString());
				
				// 새로운 쿠키를 만드는 것
				// String으로 들어가야 하기 때문에 json.toJSONString() 해당 형태로 json을 추가해주면 된다
				Cookie viewCookie = new Cookie("viewed", json.toJSONString());
				
				// 쿠키의 경로 설정
				viewCookie.setPath("/service/board");
				
				
				response.addCookie(viewCookie);
			} else {
				// 이 글을 오늘 처음 보지만 쿠키는 없는 경우
				
				// 값으로 꺼낸것이 자바스크립트 배열이기 때문에 JSONArray로 받음
				// ids라는 키 값에 넣어놓은 것이 배열이기 때문에 배열로 꺼내주면 된다
				// 있는 쿠키를 꺼내 쓰는 것
				JSONArray ids = (JSONArray) obj.get("ids");
				ids.add(board_id);
				obj.put("ids", ids);
				
				Cookie viewCookie = new Cookie("viewed", obj.toJSONString());
				viewCookie.setPath("/service/board");
				
				response.addCookie(viewCookie);
			}
		}
		
		// 글의 상세 내용을 가져온다 (DAO - 영속 계층)
		return board_mapper.get(board_id);
		
	}


}
