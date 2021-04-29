package com.stfluffy.currencyconverter.filter;

import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@Order(1)
@AllArgsConstructor
public class CookieFilter implements Filter {

    private final HttpServletResponse response;

    private final HttpServletRequest request;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws ServletException, IOException {

        Optional<String> cookie = getCookie();

        if (!cookie.isPresent()) {
            response.addCookie(new Cookie("helloWorldCookie", "hi"));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public Optional<String> getCookie() {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie helloWorldValue = Arrays.stream(cookies)
                    .filter(x -> x.getName().equals("helloWorldCookie")).findFirst().orElse(null);

            if (helloWorldValue != null && helloWorldValue.getValue() != null) {
                return Optional.of(String.valueOf(helloWorldValue.getValue()));
            }
        }
        return Optional.empty();
    }
}
