package com.backbase.movie.controller;

import com.backbase.movie.data.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.util.ReflectionTestUtils;

import java.net.URISyntaxException;

@SpringBootTest(properties = "spring.profiles.active=test", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    private static final long ID = 1L;
    @LocalServerPort
    int randomServerPort;

    @InjectMocks
    UserController userController;
    private static String BASE_URL;

    @BeforeAll
    public static void init() {
        BASE_URL = "http://localhost:";
    }

    @Test
    void testLogin() throws URISyntaxException {
        ReflectionTestUtils.invokeMethod(userController, "getJWTToken", "abc");
        User response = userController.login("abc", "abc");
    }
}