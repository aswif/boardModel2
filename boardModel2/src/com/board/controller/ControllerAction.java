package com.board.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ControllerAction extends HttpServlet {
	
	private Map commandMap = new HashMap();	// 명령어와 명령어 처리 클래스를 쌍으로 저장
	
	public void init(ServletConfig config) throws ServletException {
		loadProperties("com/board/properties/Command");
	}
	
	// properties 설정
	private void loadProperties(String path) {
		
		// 누구를 실행할지를 rb에 저장
		ResourceBundle rbHome = ResourceBundle.getBundle(path);
		
		//actionEnumHome에 path의 내용인 /list.do=com.board.action.ListAction이 literally 저장되는 듯 하다
		Enumeration<String> actionEnumHome = rbHome.getKeys();
		
		while(actionEnumHome.hasMoreElements()) {
			
			// command에 = 이전 문자열인 /list.do가 저장되는 듯 하고 (key 값)
			// className에 = 다음 문자열인 com.board.action.ListAction가 저장되는 듯 하다 (value 값)
			String command = actionEnumHome.nextElement();
			String className = rbHome.getString(command);
			
			try {
				// 해당 문자열을 클래스로 생성
				Class commandClass = Class.forName(className);
				
				// 해당 클래스의 객체를 생성
				Object commandInstance = commandClass.newInstance();
				
				// Map 객체인 commandMap에 객체 저장
				commandMap.put(command, commandInstance);
			
			} catch (ClassNotFoundException e) {
				
				continue;	// error				
				// throw new ServletException(e);			
			
			} catch (InstantiationException e) {
				
				e.printStackTrace();
			
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// get방식과 post방식을 모두 requestPro로 처리
		requestPro(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		requestPro(request, response);
	}
	
	// 사용자의 요청을 분석해서 해당 작업을 처리
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String view = null;
		
		CommandAction com = null;
		
		try {
			
			/*
			 *  request.getRequestURI()는 웹전체 경로(프로젝트명 + 파일 경로)까지 반환한다.
			 *  요청 예시 : http://localhost:8080/example/test.jsp
			 *  리턴값 : /example/test.jsp
			 */
			// 여기서는 /board/list.do가 저장된다
			String command = request.getRequestURI();
			
			/*
			 * request.getContextPath()는 프로젝트의 Context path명을 반환한다.
			 * 요청 예시 : http://localhost:8080/example/test.jsp
			 * 리턴값 : /example
			 */
			if(command.indexOf(request.getContextPath()) == 0)
				// 프로젝트의 Context path명을 지우고 파일명을command에 저장하는듯?
				// 여기서는 /board가 사라지고 /list.do만 남는다
				command = command.substring(request.getContextPath().length());
			
			// com에 ListAction instance가 저장
			com = (CommandAction) commandMap.get(command);
			
			if(com == null) {
				System.out.println("not found : " + command);
				
				return;
			}
			
			// 여기서 비즈니스 로직 별로 Action을 수행한다
			view = com.requestPro(request, response);
			
			if(view == null)
				return;
		
		} catch (Throwable e) {
			throw new ServletException(e);
		}
		
		if(view == null)
			return;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		
		dispatcher.forward(request, response);
	}

}
