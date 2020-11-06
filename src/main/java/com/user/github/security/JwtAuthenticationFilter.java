package com.user.github.security;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.http.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.user.github.model.GithubUser;
import com.user.github.util.JsonUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	private static final String JWT_SECRET = "Secret0102030405060708091011#!#@$#%$&%*&-_<<<>>>abcdeFGHIJKLMNOPQR";

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

		try {
			GithubUser loginRequest = JsonUtils.fromJson(request.getInputStream(), GithubUser.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
			return authenticationManager.authenticate(authenticationToken);
		} catch (IOException e) {
			return null;			
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication authentication) throws IOException {
		UserDetails user = ((UserDetails) authentication.getPrincipal());

		List<String> permissions = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		if (JWT_SECRET == null) {
			throw new NullPointerException("Environment variable JWT_SECRET is not set");
		}

		byte[] signingKey = JWT_SECRET.getBytes();

		String token = Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
				.setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
				.setIssuer(SecurityConstants.TOKEN_ISSUER)
				.setAudience(SecurityConstants.TOKEN_AUDIENCE)
				.setSubject(user.getUsername())
				.setExpiration(new Date(SecurityConstants.TOKEN_EXPIRATION_MS + System.currentTimeMillis()))
				.claim("permissions", permissions).compact();
		Cookie sessionCookie = new Cookie(SecurityConstants.TOKEN_HEADER, token);
		sessionCookie.setDomain("");
		sessionCookie.setPath("/");
		response.addCookie(sessionCookie);
		response.setContentType("application/json");
		response.getWriter().write("{\"token\":\"" + token + "\"}");
	}
}