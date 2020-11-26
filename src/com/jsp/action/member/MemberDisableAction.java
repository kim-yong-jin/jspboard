package com.jsp.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberDisableAction implements Action {

	

	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "/member/stopSuccess";
		String id = request.getParameter("id");
		
		try {
			
			MemberVO loginUser = (MemberVO)request.getSession().getAttribute("loginUser");
			
			if(loginUser.getId().equals(id)) {
				url = "/member/stopFail";
			}else {
				memberService.disabled(id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("id", id);
		
		return url;
	}
}
