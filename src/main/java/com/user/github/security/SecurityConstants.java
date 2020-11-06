package com.user.github.security;

import java.time.Duration;

public final class SecurityConstants {

	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_ISSUER = "secure-api";
	public static final String TOKEN_AUDIENCE = "secure-app";	
	public static final String AUTH_LOGIN_URL = "/auth";
	public static final Long TOKEN_EXPIRATION_MS = Duration.ofHours(20).toMillis();
}