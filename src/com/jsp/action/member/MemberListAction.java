package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.request.SearchCriteria;
import com.jsp.service.MemberService;

public class MemberListAction implements Action {

	
	
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "/member/list";
		
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
			
			Map<String, Object> dataMap = memberService.getSearchMemberList(cri);
			request.setAttribute("memberList", dataMap.get("memberList"));
			request.setAttribute("pageMaker", dataMap.get("pageMaker"));
			
		} catch (SQLException e) {
			url = "/error/500_error";
			e.printStackTrace();
		}
		
		return url;
	}

}
