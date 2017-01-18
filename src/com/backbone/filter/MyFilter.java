package com.backbone.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 该过滤器的作用是解决在post提交时出现的中文乱码问题
 *
 */
public class MyFilter implements Filter {

	private static String charset = "UTF-8";

	@Override
	public void destroy() {
		System.out.println("MyFilter Destroyed");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(charset);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String encording = config.getInitParameter("charset");
		if (encording != null) {
			charset = encording;
		}
	}

}
