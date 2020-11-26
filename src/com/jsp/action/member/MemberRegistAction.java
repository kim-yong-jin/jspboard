package com.jsp.action.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.util.ExceptionLoggerHelper;

public class MemberRegistAction implements Action {

	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "";
		
		MemberVO member = new MemberVO();
		
		String id = request.getParameter("id").trim();
		String pwd = request.getParameter("pwd").trim();
		String name = request.getParameter("name").trim();
		String authority = request.getParameter("authority").trim();
		String email = request.getParameter("email").trim();
		String phone = "";
		String picture = request.getParameter("picture").trim();

		for(String ph : request.getParameterValues("phone")) {
			phone += ph.trim();
		}
		
		member.setPhone(phone);
		member.setName(name);
		member.setPwd(pwd);
		member.setPicture(picture);
		member.setId(id);
		member.setEmail(email);
		member.setAuthority(authority);
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			memberService.regist(member);
			out.print("<script>");
			out.print("window.opener.location.href = '" + request.getContextPath() + "/member/list.do';");
			out.print("window.close();");
			out.print("</script>");
		} catch (SQLException e) {
			out.print("<script>");
			out.print("alert('회원가입에 실패하였습니다')");
			out.print("history.go(-1);");
			out.print("</script>");
			
			e.printStackTrace();
			
			ExceptionLoggerHelper.write(request, e, memberService);
			
		} finally {
			if(out != null) out.close();
		}
		
		return url;
	}

}
