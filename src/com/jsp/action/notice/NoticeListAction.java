package com.jsp.action.notice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.request.SearchCriteria;
import com.jsp.service.NoticeService;
import com.jsp.util.ExceptionLoggerHelper;

public class NoticeListAction implements Action {
	
	private NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "/notice/list";
		SearchCriteria cri = new SearchCriteria();
		
		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setSearchType(searchType);
		cri.setKeyword(keyword);
		
		try {
			Map<String, Object> data = noticeService.getNoticeList(cri);
			request.setAttribute("dataMap", data);
			
		} catch (Exception e) {
			ExceptionLoggerHelper.write(request, e, noticeService);
			url = null;
		}
		
		
		return url;
	}

}
