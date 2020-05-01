package com.kk.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class DispatcherServletContext implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(AppConfig.class);
		
		
		DispatcherServlet ds = new DispatcherServlet(webContext);
		
		ContextLoaderListener cls = new ContextLoaderListener(webContext);
		servletContext.addListener(cls);
		
		
		ServletRegistration.Dynamic myServlet =  servletContext.addServlet("myServlet", ds);
		myServlet.addMapping("/home.com/*");
		myServlet.setLoadOnStartup(1);
		
		
		
		
	}

}
