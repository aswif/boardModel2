package com.board.dao;

import java.sql.*;

public class CommonDao {
	
	// 공통적으로 쓰일 접속에 관련된 정보 변수들을 상수로 선언
	private final String driverName = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost:3306/BOARD?useUnicode=true&characterEncoding=euckr";
	private final String id = "board";
	private final String pw = "0000";
	
	// 접속에 필요한 변수를 선언
	private Connection con = null;
	private Statement stmt = null;
	
	
	// db 접속 정보를 가지고, 접속 후에 SQL문을 사용하기 위해 필요한 statement 객체를 반환하는
	// openConnection 메소드를 구현
	public Statement openConnection() {
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, id, pw);
			stmt = con.createStatement();
			
		} catch(Exception e) {
			System.err.println("MySQL Database Connection Something Problem!!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return stmt;
	}
	
	// 접속을 종료하기 위한 closeConnection 메소드를 구현
	public void closeConnection() {
		try {
			if (!con.isClosed())	// 닫히지 않았으면
				con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
