package com.honliv.wechat.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * @author wangdesen
 * 
 * 增加CORS支持
 * */
@Component
public class WechatServiceCORSFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse; 
		//String origin = (String) servletRequest.getRemoteHost()+":"+servletRequest.getRemotePort(); 
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); 
		response.setHeader("Access-Control-Max-Age", "3600"); 
		response.setHeader("Access-Control-Allow-Headers", "Content-Type"); 
		response.setHeader("Access-Control-Allow-Credentials","true"); 
		filterChain.doFilter(servletRequest, servletResponse);

	}
	
	public void destroy() {
	}

}