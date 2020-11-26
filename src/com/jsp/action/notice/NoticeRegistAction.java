package com.jsp.action.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeRegistAction implements Action {

	
	private NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "";
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		NoticeVO notice = new NoticeVO();
		
		notice.setContent(content);
		notice.setTitle(title);
		notice.setWriter(writer);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			noticeService.write(notice);
			out.print("<script>");
			out.print("window.opener.location.href='" + request.getContextPath() + "/notice/list.do';  window.close();");
			out.print("</script>");
			
		} catch (SQLException e) {
			out.print("<script>");
			out.print("history.go(-1)");
			out.print("</script>");
		}
		
		return url;
	}

}
