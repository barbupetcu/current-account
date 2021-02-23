package com.test.current.account.infrastructure.security;

import com.test.current.account.infrastructure.security.jwt.CustomUserAuthenticationConverter;
import com.test.current.account.infrastructure.security.jwt.IssuerClaimVerifier;
import com.test.current.account.infrastructure.security.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@RequiredArgsConstructor
public class SecurityConfig extends ResourceServerConfigurerAdapter {

    private final JwtProperties jwtProperties;

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security
                .requestMatchers().antMatchers("/api/v1/transaction/**")
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .anonymous().disable();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(jwtProperties.getSecret());
        converter.setAccessTokenConverter(tokenConverter());
        converter.setJwtClaimsSetVerifier(issuerClaimVerifier());
        return converter;
    }

    @Bean
    public AccessTokenConverter tokenConverter() {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter());
        return accessTokenConverter;
    }

    @Bean
    public CustomUserAuthenticationConverter userAuthenticationConverter() {
        return new CustomUserAuthenticationConverter();
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    @Bean
    public IssuerClaimVerifier issuerClaimVerifier() {
        return new IssuerClaimVerifier(jwtProperties.getIssuer());
    }
}