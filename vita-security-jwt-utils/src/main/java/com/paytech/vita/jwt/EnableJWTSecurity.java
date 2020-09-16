package com.paytech.vita.jwt;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
@Import({ CorsFilter.class, OAuth2ResourceServerConfig.class, ClientWebSecurityConfiguration.class })
@Configuration
public @interface EnableJWTSecurity {

}
