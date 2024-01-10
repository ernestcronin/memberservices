package com.biz.memberservices.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1155521260623735491L;
	private final UserPrincipal principal;

	public UserPrincipalAuthenticationToken(UserPrincipal principal) {
		super(principal.getAuthorities());
		this.principal = principal;
		setAuthenticated(Boolean.TRUE);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public UserPrincipal getPrincipal() {
		return principal;
	}

	@Override
	public boolean equals(Object principal) {
		if (!(principal instanceof UserPrincipal))
			return false;
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		if (!userPrincipal.getEmail().equals(this.principal.getEmail())) {
			return false;
		}
		if (!userPrincipal.getUserId().equals(this.principal.getUserId())) {
			return false;
		}
		if (!userPrincipal.getPassword().equals(this.principal.getPassword())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int code = 31;
		if (this.getPrincipal() != null) {
			code ^= this.getPrincipal().hashCode();
		}
		if (this.getCredentials() != null) {
			code ^= this.getCredentials().hashCode();
		}
		if (this.getDetails() != null) {
			code ^= this.getDetails().hashCode();
		}
		if (this.isAuthenticated()) {
			code ^= -37;
		}
		return code;
	}

}
