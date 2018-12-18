package com.ishita.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ishita.pojos.User;



public class UserInterceptor extends HandlerInterceptorAdapter{
	String errorPage;

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		User u =(User)request.getAttribute("user");
		
		
		if(u != null){
			
			if(u.getRole().equals("host")){
			System.out.println("in interceptor");
			
			return true;
			}else if(u.getRole().equals("traveller")){
				System.out.println("in interceptor");
				return true;}
			
			
				System.out.println("no user");
				response.sendRedirect(errorPage);
				return false;
			
				
			}
		

			
						System.out.println("no user");
						response.sendRedirect(errorPage);
						return false;
						
						
					
		
		
	

		
}}
