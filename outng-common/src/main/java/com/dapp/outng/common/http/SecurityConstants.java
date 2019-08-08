package com.dapp.outng.common.http;

import lombok.experimental.UtilityClass;
import org.apache.http.HttpHeaders;

@UtilityClass
public class SecurityConstants {

    // JWT Token Constants
    public static final String TOKEN_HEADER = HttpHeaders.AUTHORIZATION;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ROLE_KEY = "rol";

    public static final String USER_UNAUTHORIZED_MESSAGE = "We were not able to authenticate you. Please log in using valid credentials";
}
