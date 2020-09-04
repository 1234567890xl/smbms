package com.xl.smbms.init;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xl.smbms.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.xl.smbms.entity.Role;

public class Initializer implements ServletContextListener {
	
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		
		UserService us = wac.getBean(UserService.class);
		
		List<Role> roles =  us.getAllRoles();
		
		System.out.println(roles);
		
		arg0.getServletContext().setAttribute("APPLICATION_ROLE", roles);
	}
	
	
	
}
