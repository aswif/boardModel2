package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import com.board.beans.Board;
import com.board.controller.CommandAction;
import com.board.dao.BoardDao;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		ArrayList<Board> articleList = BoardDao.getInstance().getArticleList();
		
		// 셋팅된 리스트를 뷰에 포워드
		request.setAttribute("articleList", articleList);
		
		return "list.jsp";
	}
	
}
