package com.jsp.action.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeDetailAction implements Action {

	
	private NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "/notice/detail";
		String nno = request.getParameter("nno");
		
		try {
			NoticeVO notice = noticeService.getNotice(Integer.parseInt(nno));
			request.setAttribute("notice", notice);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
}
