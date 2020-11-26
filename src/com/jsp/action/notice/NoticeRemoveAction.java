package com.jsp.action.notice;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;


public class NoticeRemoveAction implements Action {
	private NoticeService service;
	
	public void setService(NoticeService service) {
		this.service = service;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/notice/removeSuccess";
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		NoticeVO notice;
		
		try {
			notice = service.getNotice(nno);
			
			request.setAttribute("notice", notice);
			service.remove(nno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

}
