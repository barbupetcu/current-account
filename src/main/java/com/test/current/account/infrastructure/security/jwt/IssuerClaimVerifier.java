package com.test.current.account.infrastructure.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@RequiredArgsConstructor
public class IssuerClaimVerifier implements JwtClaimsSetVerifier {

    private final String issuer;

    @Override
    public void verify(Map<String, Object> claims) throws InvalidTokenException {

        if (!CollectionUtils.isEmpty(claims) && claims.containsKey(JwtClaims.ISS)) {
            String jwtIssuer = (String)claims.get(JwtClaims.ISS);
            if (!jwtIssuer.equals(this.issuer)) {
                throw new InvalidTokenException("Invalid Issuer (iss) claim: " + jwtIssuer);
            }
        }
    }
}
