<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시판 연습 - 게시글 내용</title>
<style type="text/css">
	table, td, th {
		border:1px solid black;
	}
	th {
		background-color:black;
		color:white;
	}
</style>
</head>

<body>
	<h1>게시글 조회</h1>
	<table>
		<tr>
			<th>번호</th>
			<td>${article.idx}</td>
			<th>작성자</th>
			<td>${article.writer}</td>
			<th>날짜</th>
			<td>${article.regdate}</td>
			<th>조회수</th>
			<td>${article.count}</td>
		</tr>
		<tr>
			<th colspan="2">제목</th>
			<td colsapn="6">${article.title}</td>
		</tr>
		<tr>
			<th colspan="2">내용</th>
			<td colsapn="6">${article.content}</td>
		</tr>
	</table>
	<a href="delete.do?idx=${article.idx}">게시글삭제</a>
	<a href="list.do">목록으로</a>
	
</body>
</html>