package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;
import com.jsp.util.ExceptionLoggerHelper;

public class BoardModifyFormAction implements Action{
	private BoardService service;
	public void setService(BoardService service) {
		this.service = service;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/board/modify";
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		
		try {
			BoardVO board = service.getBoardModify(bno);
			request.setAttribute("board", board);
		} catch (SQLException e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, service);
			url = null;
		}
		return url;
	}

}
