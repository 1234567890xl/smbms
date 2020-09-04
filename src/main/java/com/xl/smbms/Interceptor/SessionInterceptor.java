package com.xl.smbms.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("SessionInterceptor:postHandle");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("SessionInterceptor:preHandle");
		
		if(request.getSession().getAttribute("USER_SESSION") == null){
			
			request.setAttribute("error", "���¼��");
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
			return false;
			
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
