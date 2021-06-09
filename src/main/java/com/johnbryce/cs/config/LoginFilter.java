package com.johnbryce.cs.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.cs.security.TokensManager;

import javassist.NotFoundException;

@Component
@Order(2)
public class LoginFilter implements Filter {
	@Autowired
	private TokensManager tokensManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		// Exclude : "Register", "Login"
		String pageRequested = req.getRequestURL().toString();
		System.out.println(pageRequested);

		// System.out.println(pageRequested.contains("/login"));
		if (pageRequested.contains("/login")) {
			chain.doFilter(request, response);
			return;
		}
//		if (pageRequested.endsWith("/customers") && req.getMethod().toString().equals("POST")) {
//	            chain.doFilter(request,response);
//	            return;
//	        }

		String token = req.getHeader("Authorization");

		// String token = req.getParameter("token");
		System.out.println("Token : " + token);

		if (token != null) {
			try {
				tokensManager.ifTokenExists(token);
				// request.setAttribute("token",token);
				chain.doFilter(request, response);
				return;
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
		HttpServletResponse res = (HttpServletResponse) response;
		// U're not logged in
		res.setStatus(401);
	}
}
