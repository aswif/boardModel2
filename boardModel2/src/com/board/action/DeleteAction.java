package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.controller.CommandAction;
import com.board.dao.BoardDao;

public class DeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String idx = request.getParameter("idx");
		
		BoardDao.getInstance().deleteArticle(idx);
		
		return "delete.jsp";
	}

}
