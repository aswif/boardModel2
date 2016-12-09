package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.beans.Board;
import com.board.controller.CommandAction;
import com.board.dao.BoardDao;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String idx = request.getParameter("idx");
		
		Board article = BoardDao.getInstance().getArticle(idx);
		
		// 셋팅된 Board beans 값을 뷰에 포워드
		request.setAttribute("article", article);
		
		return "content.jsp";
	}

	
}
