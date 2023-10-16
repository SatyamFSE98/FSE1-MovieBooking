package com.moviebooking.auth.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtFilter extends GenericFilterBean {

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// capture the request and validation of token,user here
		HttpServletRequest httpReq = (HttpServletRequest) request;
		System.err.println("this filter from auth ms");
		try {
			String jwtToken = getJwtToken(httpReq);//extract the token from the request
			if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)) {
				String username = jwtUtils.getUserNameFromJwtToken(jwtToken);
				Claims claims = jwtUtils.getJwtClaims(jwtToken);
				httpReq.setAttribute(username, claims);

			}
		} catch (Exception e) {
			System.err.println("Token is not valid");
		}

		chain.doFilter(request, response);

	}

	// utility method for getting jwt token here
	private String getJwtToken(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		
		return null;
	}

}
