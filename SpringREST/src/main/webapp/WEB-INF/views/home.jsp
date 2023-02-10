<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rest Home</title>
</head>
<body>

	<h3># Rest 요청 보내보기</h3>
	
	<ul>
		<li><a href="/springrest/restful/test1">Hello</a></li>
		<li><a href="/springrest/restful/pizza1">pizza1 (JSON, 수동)</a></li>
		<li><a href="/springrest/restful/pizza2">pizza2 (JSON, 자동)</a></li>
		<li><a href="/springrest/restful/pizza3">pizza3 (XML)</a></li>
	</ul>
	
	<h3># ResponseEntity로 응답 직접 생성하기</h3>
	
	<ul>
		<li><a href="/springrest/restful/ok1">ok1</a></li>
		<li><a href="/springrest/restful/ok2">ok2</a></li>
		<li><a href="/springrest/restful/ok3">ok3</a></li>
		<li><a href="/springrest/restful/status1">status1 (502 Bad Gateway)</a></li>
		<li><a href="/springrest/restful/status2">status2 (404 Not Found)</a></li>
	</ul>

</body>
</html>