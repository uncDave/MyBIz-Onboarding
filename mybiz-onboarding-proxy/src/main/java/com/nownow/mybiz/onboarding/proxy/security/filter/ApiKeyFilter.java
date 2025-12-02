package com.nownow.mybiz.onboarding.proxy.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${myBiz.api-key}")
    private String validApiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        log.info("Incoming Request URI: {}", requestURI);

        if (isExemptedUrl(requestURI)) {
            log.info("Excluding request: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        String apiKey = request.getHeader("X-API-KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            log.warn("Missing API key");
            sendErrorResponse(response, "Missing API key");
            return;
        }

        if (!apiKey.equals(validApiKey)) {
            log.warn("Invalid API key: {}", apiKey);
            sendErrorResponse(response, "Invalid API key");
            return;
        }

        log.info("API key validated successfully");
        filterChain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + message + "\"}");
    }

    private boolean isExemptedUrl(String requestURI) {
        boolean exempted = requestURI != null && (
                requestURI.startsWith("/swagger-ui/") ||
                        requestURI.equals("/proxy/swagger-ui.html") ||
                        requestURI.equals("/proxy/swagger-ui/index.html") ||
                        requestURI.startsWith("/proxy/swagger-ui/") ||
                        requestURI.startsWith("/v3/api-docs") ||
                        requestURI.startsWith("/actuator/")
        );
        log.info("Checking URL exemption - URI: {}, Exempted: {}", requestURI, exempted);
        return exempted;
    }
}
