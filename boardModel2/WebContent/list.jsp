<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시판 연습 - 게시글 리스트</title>
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
	<h1>게시글 리스트</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${articleList}" var="article">
			<tr>
				<td>${article.idx}</td>
				<td><a href='content.do?idx=${article.idx}'>${article.title}</a></td>
				<td>${article.writer}</td>
				<td>${article.regdate}</td>
				<td>${article.count}</td>
			</tr>
		</c:forEach>		
	</table>
	<a href="write.jsp">글쓰기</a>

</body>
</html>