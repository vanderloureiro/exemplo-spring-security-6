package dev.vanderloureiro.exemplosecurity.core;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Slf4j
@Service
public class CustomBasicAuthFilter extends OncePerRequestFilter {

    private static final int BEARER_LENGTH = 6;
    private static final String EXAMPLE_USERNAME = "usuario";
    private static final String EXAMPLE_PASSWORD = "senha";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var headerAuthorization = request.getHeader("Authorization");
        log.info(headerAuthorization);
        if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
            filterChain.doFilter(request, response);
            return;
        }
        var basicToken = headerAuthorization.substring(BEARER_LENGTH);
        byte[] basicTokenDecoded = Base64.getDecoder().decode(basicToken);
        String basicTokenValue = new String(basicTokenDecoded);
        basicTokenValue.split(":");
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            var authToken = new UsernamePasswordAuthenticationToken(null, null, null);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}
