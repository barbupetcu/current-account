package com.test.current.account.infrastructure.security.jwt;

import com.test.current.account.infrastructure.security.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomUserAuthenticationConverter implements UserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {

        UserDetails userDetails = (UserDetails) userAuthentication.getPrincipal();

        Map<String, String> convertResult = new HashMap<>();

        convertResult.put(JwtClaims.NAME, userDetails.getName());
        convertResult.put(JwtClaims.ACCOUNT_ID, userDetails.getAccountId());

        return convertResult;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        UserDetails userDetails = UserDetails.builder()
                .accountId((String)map.get(JwtClaims.ACCOUNT_ID))
                .name((String)map.get(JwtClaims.NAME))
                .build();

        return new JwtAuthentication(userDetails);
    }
}
