package com.ezen.springrest;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.springrest.dto.Pizza;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class RestSampleController {

	// produces : 아래 메서드에서 어떤 타입의 데이터를 생산 할 것인지(=생산품의 타입)
	@GetMapping(value = {"/restful/test1"}, produces = "text/plain; charset=UTF-8")
	public String test1() {
		log.info("요청을 받았습니다");
		
		return "Hi! 안녕!";
	}

	// JSON 형태의 문자열을 응답하면서 해당 데ㅣ터가 json이라고 표시해줄수있다
	@GetMapping(value = {"/restful/pizza1"}, produces = "application/json; charset=UTF-8")
	public String pizza1() {
		
		return "{\"name\":\"포테이토피자\",\"price\":null,\"calories\":2041.38}";
	}
	
	// JSON 타입의 데이터를 쉽게 응답하기 위해서는 jackson-databind라는 라이브러리를 사용한다
	@GetMapping(value = {"/restful/pizza2"}, produces = "application/json; charset=UTF-8")
	public Pizza pizza2() {
		Pizza pizza = new Pizza();
		
		pizza.setName("콤비네이션피자");
		pizza.setPrice(null);
		pizza.setCalories(1888.38);
		
		return pizza;
	}

	
	// XML타입으로 자동변환 하기 위해서는
	@GetMapping(value = "/restful/pizza3", produces = MediaType.APPLICATION_XML_VALUE)
	public Pizza pizza3() {
		Pizza pizza = new Pizza();
		
		pizza.setName("페퍼로니피자");
		pizza.setPrice(8000);
		pizza.setCalories(2111.23);
		
		return pizza;
	}
	
	@GetMapping(value = "/restful/ok1")
	public ResponseEntity<String> ok1() {
		// ResponseEntity : 개발자가 원하는 응답을 마음대로 생성하여 응답한다
		
		// * 상태코드란? 404 메세지와 같은 웹페이지의 상태코드
		// ResponseEntity.ok : 상태코드가 200인 응답을 바로 생성한다 (상태코드 200은 정상적인 통신 성공)
		// ResponseEntity.ok(body) : body는 데이터를 실어보내느 곳을 의미한다
		
		return ResponseEntity.ok("<html><head></head><body><h3>Hello!</h3></body></html>");
	}
	
	@GetMapping(value = "/restful/ok2")
	public ResponseEntity<String> ok2(){
		// Bodybuilder 방식
		return ResponseEntity
				.ok()
				.contentType(MediaType.TEXT_HTML)
				.body("<html><head></head><body><h3>Hello!</h3></body></html>");
		
	}
	
	@GetMapping(value = "restful/ok3")
	public ResponseEntity<Pizza> ok3(){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(new Pizza("하와이안 피자", 11200, 2333.33));
	}
	
	@GetMapping(value = "/restful/status1")
	public ResponseEntity<String> status1() {
		return ResponseEntity
				.status(HttpStatus.BAD_GATEWAY)
				.contentType(MediaType.TEXT_PLAIN)
				.body("Bad Gateway...");
	}
	
	// 페이지가 있는데 없음(의도한 404 오류) - 잘못된 경로로 들어온 유저를 따로 DB에 올린다던가의 조취 가능
	@GetMapping(value = "restful/status2")
	public ResponseEntity<String> status2() {
		return ResponseEntity
				.notFound().build();
	}
	
	// PUT - 해당 데이터의 전체 수정
	// PATCH - 해당 데이터의 일부 수정 (나머지는 기존 값 유지)
//	@PutMapping(value = "/restful/pizza1")
//	public ResponseEntity<Pizza> updatePizza(@RequestBody Pizza pizza) {
//		
//		return null;
//		
//	}
}
