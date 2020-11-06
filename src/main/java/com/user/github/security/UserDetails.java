package com.user.github.security;

import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
public class UserDetails {

	private final String username, authorization;
	
	public String getToken() {
		if (authorization != null && authorization.startsWith(SecurityConstants.TOKEN_PREFIX))
			return authorization.substring(SecurityConstants.TOKEN_PREFIX.length());
		return authorization;
	}

	public boolean isLogged() {
		return username != null && !username.strip().isEmpty() && authorization != null && !authorization.strip().isEmpty();
	}
}
