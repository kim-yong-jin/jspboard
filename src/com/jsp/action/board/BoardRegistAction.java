package com.jsp.action.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

public class BoardRegistAction implements Action {

	private BoardService service;
	
	public void setService(BoardService service) {
		this.service = service;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url ="";
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		BoardVO board = new BoardVO();
		
		board.setContent(content);
		board.setTitle(title);
		board.setWriter(writer);
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out =response.getWriter();
		
		try {
			service.write(board);
			out.print("<script>");
			out.print("window.opener.location.href='" + request.getContextPath() + "/board/list.do';  window.close();");
			out.print("</script>");
		} catch (SQLException e) {
			out.print("<script>");
			out.print("history.go(-1)");
			out.print("</script>");
		}
		
		
		return url;
	}

}
