<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�Խ��� ���� - �Խñ� ����</title>
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
	<h1>�Խñ� ��ȸ</h1>
	<table>
		<tr>
			<th>��ȣ</th>
			<td>${article.idx}</td>
			<th>�ۼ���</th>
			<td>${article.writer}</td>
			<th>��¥</th>
			<td>${article.regdate}</td>
			<th>��ȸ��</th>
			<td>${article.count}</td>
		</tr>
		<tr>
			<th colspan="2">����</th>
			<td colsapn="6">${article.title}</td>
		</tr>
		<tr>
			<th colspan="2">����</th>
			<td colsapn="6">${article.content}</td>
		</tr>
	</table>
	<a href="delete.do?idx=${article.idx}">�Խñۻ���</a>
	<a href="list.do">�������</a>
	
</body>
</html>