package com.jsp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;

public class MemberDisabledFilter implements Filter {
	
	private List<String> checkURLs = new ArrayList<String>();
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest)arg0;
		HttpServletResponse httpRes = (HttpServletResponse)arg1;
		
		String uri = httpReq.getRequestURI();
		
		MemberVO loginUser = (MemberVO)httpReq.getSession().getAttribute("loginUser");
		
		if(loginUser != null && loginUser.getEnabled() != 1) {
			for(String url : checkURLs) {
				
				if(uri.contains(url)) {
					url = "/common/checkDisabled";
					ViewResolver.view(httpReq, httpRes, url);
					return;
				}
				
			}
		}
		
		
		arg2.doFilter(arg0, arg1);
		
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		String paramValue = arg0.getInitParameter("checkURL");
		StringTokenizer st = new StringTokenizer(paramValue, ",");
		while(st.hasMoreTokens()) {
			
			String urlKey = st.nextToken();
			checkURLs.add(urlKey);
			
		}

	}

}
