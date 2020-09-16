package com.paytech.vita.jwt;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class ClientJwtDecodeHandler {

	@SuppressWarnings("unchecked")
	public Map<String, Object> getDetailJwtObject() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object details = authentication.getDetails();
		if (details instanceof OAuth2AuthenticationDetails) {
			OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) details;
			return (Map<String, Object>) oAuth2AuthenticationDetails.getDecodedDetails();

		}
		return null;
	}
}
