package com.paytech.vita.oauth2.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.paytech.vita.oauth2.entity.User;

public class CustomAccessTokenConverter extends JwtAccessTokenConverter {

//	private Map<String, String> customHeaders = new HashMap<>();
//	private JsonParser objectMapper = JsonParserFactory.create();
//	final RsaSigner signer;
//
//	public CustomTokenEnhancer(Map<String, String> customHeaders, KeyPair keyPair) {
//		super();
//		super.setKeyPair(keyPair);
//		this.signer = new RsaSigner((RSAPrivateKey) keyPair.getPrivate());
//		this.customHeaders = customHeaders;
//	}

//	@Override
//	protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//		String content;
//		try {
//			content = this.objectMapper
//					.formatMap(getAccessTokenConverter().convertAccessToken(accessToken, authentication));
//		} catch (Exception ex) {
//			throw new IllegalStateException("Cannot convert access token to JSON", ex);
//		}
//		return JwtHelper.encode(content, this.signer, this.customHeaders).getEncoded();
//	}

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = (User) authentication.getPrincipal();

		Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());

		info.put("email", user.getEmail());
		info.put("phone", user.getPhoneNumber());
		info.put("change_pass_request", user.isPassChangeRequest());
		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);

		return super.enhance(customAccessToken, authentication);
	}
}
