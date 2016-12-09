package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.controller.CommandAction;
import com.board.dao.BoardDao;

public class InsertAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("euc-kr");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String regdate = request.getParameter("regdate");
		String content = request.getParameter("content");
				
		BoardDao.getInstance().insertArticle(title, writer, regdate, content);
		
		return "list.do";
	}

}
