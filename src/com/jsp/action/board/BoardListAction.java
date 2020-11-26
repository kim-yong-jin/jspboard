package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.NoticeService;
import com.jsp.util.ExceptionLoggerHelper;

public class BoardListAction implements Action{
	private BoardService service;
	
	public void setService(BoardService service) {
		this.service = service;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/board/list";
		
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
			Map<String, Object> dataMap = service.getBoardList(cri);
			request.setAttribute("dataMap", dataMap);
		} catch (SQLException e) {
			ExceptionLoggerHelper.write(request, e, service);
			e.printStackTrace();
			url = null;
		}
		return url;
	}

}
