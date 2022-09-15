package com.backbase.movie.security;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.profiles.active=test", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JWTAuthorizationFilterTest {
    @InjectMocks
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    FilterChain filterChain;

    @Test
    void testDoFilterInternal() throws ServletException, IOException {
        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer");
        jwtAuthorizationFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
    }

}