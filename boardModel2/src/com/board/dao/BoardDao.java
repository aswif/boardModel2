package com.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.board.beans.Board;

public class BoardDao extends CommonDao {
	
	// 싱글톤 패턴으로,
	// BoardDao 객체는 여러 번 호출하더라도 단 한 번만 생성
	public static BoardDao getInstance() {
		BoardDao _instance = new BoardDao();
		return _instance;
	}
	
	
	/*
	 * 게시판 리스트를 출력하기 위해 데이터 값에 접근하는 메서드
	 * db의 튜플 별로 beans를 ArrayList에 담아 ListAction에 전달
	 */
	public ArrayList<Board> getArticleList() throws SQLException {
		ResultSet rs = null;
		String sql = "SELECT * from BOARD order by idx desc";
		
		// sql을 실행하기 위해 연결을 열어 쿼리를 실행하고 rs에 반환
		rs = openConnection().executeQuery(sql);
		
		// Board형 배열 객체를 선언
		ArrayList<Board> articleList = new ArrayList<Board>();
		
		while(rs.next()) {
			Board article = new Board();	// 데이터들을 담기 위해 Board객체에 메모리를 할당
			
			article.setIdx(rs.getInt("idx"));
			article.setTitle(rs.getString("title"));
			article.setWriter(rs.getString("writer"));
			article.setRegdate(rs.getString("regdate"));
			
			articleList.add(article);	// 셋팅된 빈을 리스트에 추가
		}
		
		closeConnection();	// 연결을 닫는다
		
		return articleList;
	}
	
	
	/*
	 * 게시판 글 하나의 정보를 출력하기 위해 데이터 값에 접근하는 메서드
	 * db 하나의 튜플을 가리키는 beans 정보 만을 반환하면 되기 때문에
	 * ArrayList를 사용하지 않고 Board 객체를 사용, ContentAction에 전달
	 */
	public Board getArticle(String idx) throws SQLException {	
		ResultSet rs = null;
		String sql = "SELECT * from board where idx = " + idx;
		
		// sql을 실행하기 위해 연결을 열어 쿼리를 실행하고 rs에 반환
		rs = openConnection().executeQuery(sql);
		
		Board article = new Board();	// 데이터들을 담기 위해 Board객체에 메모리를 할당
		
		while(rs.next()) {
			article.setIdx(rs.getInt("idx"));
			article.setWriter(rs.getString("writer"));
			article.setRegdate(rs.getString("regdate"));
			article.setCount(rs.getInt("count"));
			article.setTitle(rs.getString("title"));
			article.setContent(rs.getString("content"));
		}
		
		closeConnection();	// 연결을 닫는다
		
		return article;
	}
	
	
	/*
	 * db의 튜플 하나를 삭제하기만 하면 되므로 값을 리턴할 필요 X
	 * 전달 받은 인덱스 값에 해당하는 튜플을 삭제하는 명령 수행
	 */
	public void deleteArticle(String idx) throws SQLException {
		
		String sql = "DELETE from board where idx = " + idx;
		
		// idx에 해당하는 튜플을 삭제
		openConnection().executeUpdate(sql);
		
		closeConnection();		
	}
	
	
	/*
	 * 게시판에 글을 삽입하는 명령을 수행하는 메서드
	 * db에 데이터를 입력하기만 하고 따로 출력할 필요는 없으므로 query만 수행하고 리턴 값은 필요 X
	 * <참고>
	 * executeQuery(): ResultSet을 얻기 위한 메서드. 주로 select 문에 사용
	 * executeUpdate(): 적용된 행의 갯수를 얻기 위한 메서드.
	 * 					DDL(insert, update, delete), DML(create, drop, alter)에 사용
	 */
	public void insertArticle(String title, String writer, String regdate, String content) throws SQLException {
		ResultSet rs = null;
		
		int currval = 0;		// 게시글의 인덱스 번호를 저장하기 위한 int형 변수
		String curDate = null;	// 게시글 입력 날짜를 저장하기 위한 String 변수
		
		String updateIdxSql = "SELECT ifnull(max(idx), 0)+1 from board";
		String updateDateSql = "SELECT CURRENT_TIMESTAMP";
		
		rs = openConnection().executeQuery(updateIdxSql);
		if(rs.next())
			currval = rs.getInt(1);
		
		rs = openConnection().executeQuery(updateDateSql);
		if(rs.next())
			curDate = rs.getString("CURRENT_TIMESTAMP");
		
		String sql = "INSERT INTO BOARD " +
						"(IDX, TITLE, WRITER, REGDATE, COUNT, CONTENT)" +
						"VALUES (" + currval + ", '" + title + "', '" + 
						writer + "', '" + curDate + "', '1', '" + content + "')";
		
		openConnection().executeUpdate(sql);
		
		closeConnection();		
	}

}
