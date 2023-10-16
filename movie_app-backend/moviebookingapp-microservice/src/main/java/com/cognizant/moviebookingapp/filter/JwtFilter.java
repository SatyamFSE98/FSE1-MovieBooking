package com.cognizant.moviebookingapp.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// filter the request and validate here
		System.err.println("JwtFilter: Starting filter");
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		String authHeader = httpReq.getHeader("Authorization");

		// only allow swagger endpoints
		String path = httpReq.getRequestURI();
		System.out.println("out---");
		System.err.println(path);
		if (path.startsWith("/swagger-ui/") || path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui.html")||path.startsWith("/api/v1.0/moviebooking/")) {
			System.out.println("if---");
			System.err.println(path);
			chain.doFilter(request, response);
			return;
		}

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			httpRes.sendError(HttpServletResponse.SC_UNAUTHORIZED, "access denied");
			return;
		}
		chain.doFilter(request, response);
	}
}
