package com.technokratos.security.impl;

import com.technokratos.exceptions.token.*;
import com.technokratos.exceptions.unauthorized.UserAccountUnauthorizedException;
import com.technokratos.models.RefreshTokenEntity;
import com.technokratos.models.UserAccountEntity;
import com.technokratos.security.TokenProvider;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class TokenProviderImpl implements TokenProvider {
    @Value("${hh.jwt.secret_key}")
    private String secretKey;

    @Value("${hh.jwt.access_token.expired_time_in_millis}")
    private long expiredTimeForAccessTokenInMillis;

    @Value("${hh.jwt.refresh_token.expired_time_in_millis}")
    private long expiredTimeForRefreshTokenInMillis;

    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;

    private static final String TOKEN_PREFIX = "Bearer ";

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Override
    public String generateAccessToken(String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role",role);
        Date startTime = new Date();
        Date endTime = new Date(startTime.getTime()+ expiredTimeForAccessTokenInMillis);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(startTime)
                .setExpiration(endTime)
                .signWith(ALGORITHM,secretKey)
                .compact();
    }

    @Override
    public RefreshTokenEntity generateRefreshToken(UserAccountEntity userAccount) {
        return RefreshTokenEntity.builder()
                .createdDate(Instant.now())
                .expiredTime(Instant.now().plusMillis(expiredTimeForRefreshTokenInMillis))
                .userAccount(userAccount)
                .build();
    }

    @Override
    public boolean isValidAccessToken(String token) {
        if (token==null || !token.startsWith(TOKEN_PREFIX)) return false;
        try {
            String finalToken = parseToken(token);
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(finalToken).getBody().getSubject();
            return true;
        }catch (SignatureException e) {
            throw new SignatureTokenException();
        }catch (MalformedJwtException e) {
            throw new InvalidTokenException();
        }catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }catch (UnsupportedTokenException e) {
            throw new UnsupportedTokenException();
        }catch (IllegalArgumentException e) {
            throw new EmptyTokenException();
        }
    }

    @Override
    public Authentication getAuthenticationFromAccessToken(String token) {
        try {
            UserDetails userDetails = userDetailsService
                    .loadUserByUsername(getUsernameFromAccessToken(parseToken(token)));
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        }catch (UsernameNotFoundException | IllegalArgumentException e) {
            throw new UserAccountUnauthorizedException();
        }
    }

    private String getUsernameFromAccessToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    private String parseToken(String token) {
        if (token == null || !token.startsWith(TOKEN_PREFIX)) return null;

        return token.substring(TOKEN_PREFIX.length());
    }

    @Override
    public String getAccessTokenFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }

}
