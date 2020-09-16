package com.paytech.vita.jwt;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@EnableResourceServer
@ConditionalOnProperty(value = "security.jwt.publicKey")
@EnableConfigurationProperties(SecurityClientProperties.class)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private final SecurityClientProperties securityProperties;

	private TokenStore tokenStore;

	public OAuth2ResourceServerConfig(SecurityClientProperties securityProperties) {
		super();
		this.securityProperties = securityProperties;
	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) {
		resources.tokenStore(tokenStore());
	}

	@Bean
	public ClientAccessTokenConverter clientAccessTokenConverter() {
		return new ClientAccessTokenConverter();
	}

	@Bean
	public DefaultTokenServices tokenServices(final TokenStore tokenStore) {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore);
		return tokenServices;
	}

	@Bean
	public TokenStore tokenStore() {
		if (tokenStore == null) {
			tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
		}
		return tokenStore;
	}

	@Bean
	@Qualifier("jwtAccessTokenConverter")
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		String publicKey = getPublicKeyAsString();
		converter.setAccessTokenConverter(clientAccessTokenConverter());
		converter.setVerifierKey(publicKey);
		converter.setVerifier(new RsaVerifier(publicKey));
		return converter;
	}
	
	@Bean
	public ClientJwtDecodeHandler clientJwtDecodeHandler() {
		return new ClientJwtDecodeHandler();
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}

	private String getPublicKeyAsString() {
		try {
			return IOUtils.toString(securityProperties.getJwt().getPublicKey().getInputStream(), UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
