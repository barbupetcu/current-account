package com.test.current.account.api.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.test.current.account.api.model.CreateAccountResponse;
import com.test.current.account.api.model.JwtResponse;
import com.test.current.account.infrastructure.security.jwt.JwtProperties;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.test.current.account.api.ApiVersion.API_V1;

@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor
public class AdminController {

    public static final String JWT_PATH = "/jwt/{accountId}";

    private final JwtProperties jwtProperties;

    @Operation(summary = "Generate jwt token for test purposes.")
    @GetMapping(value = JWT_PATH)
    public JwtResponse generateJwt(@PathVariable String accountId) throws UnsupportedEncodingException {

        String jwtToken = JWT.create()
                .withClaim("name", "Petcu Barbu")
                .withClaim("accountId", accountId)
                .withIssuer(jwtProperties.getIssuer())
                .withExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusYears(100)))
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));

        return JwtResponse.aJwtResponse(jwtToken);
    }
}
