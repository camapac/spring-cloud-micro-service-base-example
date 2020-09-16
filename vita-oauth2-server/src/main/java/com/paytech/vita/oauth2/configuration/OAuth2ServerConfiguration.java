package com.paytech.vita.oauth2.configuration;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Value("${check-user-scopes}")
	private boolean checkUserScopes;


	private DataSource dataSource;
	private PasswordEncoder passwordEncoder;
	private UserDetailsService userDetailsService;
	private AuthenticationManager authenticationManager;
	private ClientDetailsService clientDetailsService;
	private SecurityProperties securityProperties;
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	private TokenStore tokenStore;

	@Autowired
	public OAuth2ServerConfiguration(final DataSource dataSource, final PasswordEncoder passwordEncoder,
			final AuthenticationManager authenticationManager, final ClientDetailsService clientDetailsService,
			final SecurityProperties securityProperties, final UserDetailsService userDetailsService) {
		this.dataSource = dataSource;
		this.passwordEncoder = passwordEncoder;
		this.clientDetailsService = clientDetailsService;
		this.authenticationManager = authenticationManager;
		this.securityProperties = securityProperties;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public OAuth2RequestFactory requestFactory() {
		CustomOauth2RequestFactory requestFactory = new CustomOauth2RequestFactory(clientDetailsService);
		requestFactory.setCheckUserScopes(true);
		return requestFactory;
	}

	@Bean
	public TokenStore tokenStore() {
		if (tokenStore == null) {
			tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
		}
		return tokenStore;
	}

	@Bean
	public DefaultTokenServices tokenServices(final TokenStore tokenStore,
			final ClientDetailsService clientDetailsService) {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setClientDetailsService(clientDetailsService);
		tokenServices.setAuthenticationManager(this.authenticationManager);
		return tokenServices;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
	}

	@Bean
	public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
		return new TokenEndpointAuthenticationFilter(authenticationManager, requestFactory());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.passwordEncoder(this.passwordEncoder).tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}
	

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtAccessTokenConverter())
				.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
		if (checkUserScopes)
			endpoints.requestFactory(requestFactory());
	}


	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {

		if (jwtAccessTokenConverter != null) {
			return jwtAccessTokenConverter;
		}
		jwtAccessTokenConverter = new CustomAccessTokenConverter();
		jwtAccessTokenConverter.setKeyPair(keyPair());
		return jwtAccessTokenConverter;
	}

	@Bean
	public KeyPair keyPair() {
		SecurityProperties.JwtProperties jwtProperties = securityProperties.getJwt();
		return keyPair(jwtProperties, keyStoreKeyFactory(jwtProperties));
	}

	private KeyPair keyPair(SecurityProperties.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory) {
		return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias(),
				jwtProperties.getKeyPairPassword().toCharArray());
	}

	private KeyStoreKeyFactory keyStoreKeyFactory(SecurityProperties.JwtProperties jwtProperties) {
		return new KeyStoreKeyFactory(new FileSystemResource(jwtProperties.getKeyStore()), jwtProperties.getKeyStorePassword().toCharArray());
	}

}
