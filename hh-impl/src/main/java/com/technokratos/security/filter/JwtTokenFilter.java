package com.technokratos.security.filter;

import com.technokratos.exceptions.token.TokenHhException;
import com.technokratos.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = tokenProvider.getAccessTokenFromHeader(request);
            if (tokenProvider.isValidAccessToken(token)) {
                SecurityContextHolder.getContext()
                        .setAuthentication(tokenProvider.getAuthenticationFromAccessToken(token));
            }
        }catch (TokenHhException e) {
            SecurityContextHolder.clearContext();
            response.setCharacterEncoding("UTF-8");
            response.setStatus(e.getHttpStatus().value());
            response.getWriter().write(e.getMessage());
        }
            filterChain.doFilter(request,response);
    }
}
