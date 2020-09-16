package com.paytech.vita.oauth2.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("security")
public class SecurityProperties {

	private JwtProperties jwt;

	public JwtProperties getJwt() {
		return jwt;
	}

	public void setJwt(JwtProperties jwt) {
		this.jwt = jwt;
	}

	public static class JwtProperties {

		private String keyStore;
		private String keyStorePassword;
		private String keyPairAlias;
		private String keyPairPassword;

		public String getKeyStore() {
			return keyStore;
		}

		public void setKeyStore(String keyStore) {
			this.keyStore = keyStore;
		}

		public String getKeyStorePassword() {
			return keyStorePassword;
		}

		public void setKeyStorePassword(String keyStorePassword) {
			this.keyStorePassword = keyStorePassword;
		}

		public String getKeyPairAlias() {
			return keyPairAlias;
		}

		public void setKeyPairAlias(String keyPairAlias) {
			this.keyPairAlias = keyPairAlias;
		}

		public String getKeyPairPassword() {
			return keyPairPassword;
		}

		public void setKeyPairPassword(String keyPairPassword) {
			this.keyPairPassword = keyPairPassword;
		}
	}
}
