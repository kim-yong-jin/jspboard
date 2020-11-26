package com.jsp.action.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;
import com.jsp.util.ExceptionLoggerHelper;

public class NoticeModifyFormAction implements Action {
	
	private NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "/notice/modify";
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		try {
			
			NoticeVO notice =  noticeService.getNoticeModify(nno);
			request.setAttribute("notice", notice);
			
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, noticeService);
			url=null;
		}
		
		
		
		return url;
	}

}
