package com.jsp.action.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeModifyAction implements Action {

	private NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "redirect:/notice/detail.do?nno=";
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		NoticeVO notice = new NoticeVO();
		
		notice.setNno(nno);
		notice.setContent(content);
		notice.setTitle(title);
		notice.setWriter(writer);
		
		try {
			noticeService.modify(notice);
			url = url + nno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}

}
