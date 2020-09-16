package com.paytech.vita.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties("security")
public class SecurityClientProperties {

	private JwtProperties jwt;

	public JwtProperties getJwt() {
		return jwt;
	}

	public void setJwt(JwtProperties jwt) {
		this.jwt = jwt;
	}

	public static class JwtProperties {

		private Resource publicKey;
		
		private String value;

		public Resource getPublicKey() {
			return publicKey;
		}

		public void setPublicKey(Resource publicKey) {
			this.publicKey = publicKey;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
