package com.jsp.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.util.ExceptionLoggerHelper;

public class MemberDetailAction implements Action {

	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "/member/detail";
		String id = request.getParameter("id");
		
		try {
			MemberVO member = memberService.getMember(id);
			
			request.setAttribute("member", member);
			
		} catch (Exception e) {
			ExceptionLoggerHelper.write(request, e, memberService);
			e.printStackTrace();
			url = null;
		}
		
		return url;
	}

}
