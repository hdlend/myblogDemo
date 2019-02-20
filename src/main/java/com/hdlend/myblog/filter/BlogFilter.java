package com.hdlend.myblog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlogFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse resp = (HttpServletResponse) response;
	    try {
	    	String uri = req.getRequestURI();
	 	    String fileName = uri.substring(uri.lastIndexOf("/")+1, uri.length());
	 	    String id = fileName.split("\\.")[0];
	 	    req.setAttribute("bId", Integer.parseInt(id));
	 	    req.getRequestDispatcher("/viewBlog").forward(req, resp);
		} catch (Exception e) {
			req.getRequestDispatcher("/error/404.html").forward(req, resp);
			// TODO: handle exception
		}
	   
	    
	//	chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
