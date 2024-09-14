package com.packt.cardatabase;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.packt.cardatabase.service.JwtService;
import java.util.Collections;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
					throws ServletException, IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getRequestURI();

		if (path.startsWith("/api/product/all") || path.startsWith("/join")) {
			filterChain.doFilter(request, response);
			return;
		}
		// Get token from Authorization header
		String jws = request.getHeader(HttpHeaders.AUTHORIZATION);			

		if (jws != null) {
			// Verify token and get user
			String user = jwtService.getAuthUser(request);
			String role = jwtService.getAuthRole(request);

			if (user != null && role != null) {
				// Create an Authentication object with the user's role
				Authentication authentication = new UsernamePasswordAuthenticationToken(
						user, null, Collections.singletonList(new SimpleGrantedAuthority(role)));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}
}
