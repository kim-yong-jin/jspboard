package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.util.ExceptionLoggerHelper;

public class MemberModifyFormAction implements Action {

	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "/member/modify";
		
		String id = request.getParameter("id");
		
		try {
			MemberVO member = memberService.getMember(id);
			request.setAttribute("member", member);
		} catch (SQLException e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, memberService);
		}
		
		return url;
	}

}
