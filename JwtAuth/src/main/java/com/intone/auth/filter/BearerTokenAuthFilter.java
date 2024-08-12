package com.intone.auth.filter;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.intone.auth.model.UserRecord;
import com.intone.auth.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BearerTokenAuthFilter extends OncePerRequestFilter {
	@Autowired
	UserService userservice;
	List<String> excludedPaths = List.of("/auth/SignUp","/auth/SignIn");
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestPath = request.getRequestURI();
		if(excludedPaths.stream().anyMatch(requestPath::startsWith)) {
			filterChain.doFilter(request, response);
			return;
		}
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && !authHeader.substring(7).isBlank()) {
			String accessToken = authHeader.substring(7);
			UserRecord user = isTokenValid(accessToken);
			if(user == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;	
			}
			else {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserId(), null, null);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

	private UserRecord isTokenValid(String accessToken) {
		UserRecord user = userservice.getUserRecordByToken(accessToken);
		return user;
	}

}
