package com.example.develop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITELIST = {"/", "/users/signup", "/users/login", "logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        log.info("run login filter logic");

        if (!isWhiteList(requestURI)) {

            String sessionId = null;

            if (httpRequest.getCookies() != null) {
                for (Cookie cookie : httpRequest.getCookies()) {
                    log.info("check cookie - {} : {}", cookie.getName(), cookie.getValue());
                    if ("JSESSIONID".equals(cookie.getName())) {
                        sessionId = cookie.getValue();
                    }
                }
            }

            if (sessionId == null || sessionId.isEmpty()) {
                log.error("sessionId is empty");
                throw new ServletException("please login first");
            }

            log.info("login successful");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITELIST, requestURI);
    }
}

