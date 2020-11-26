package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

public class BoardModifyAction implements Action{
	private BoardService service;
	
	public void setService(BoardService service) {
		this.service = service;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "redirect:/board/detail.do?bno=";
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		BoardVO board = new BoardVO();
		
		board.setBno(bno);
		board.setContent(content);
		board.setTitle(title);
		board.setWriter(writer);
		
		try {
			service.modify(board);
			url = url + bno;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}

}
