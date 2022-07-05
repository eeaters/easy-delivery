package io.eeaters.easy.delivery.util;

import java.io.Serializable;

public class RedisKeyUtils {

    public static final String USERID_PREFIX = "quarkus:easy:delivery:userId:";

    public static final String TOKEN_PREFIX = "quarkus:easy:delivery:token:";


    public static String userIdKey(Serializable userId) {
        return USERID_PREFIX + userId;
    }

    public static String tokenKey(String token) {
        return TOKEN_PREFIX + token;
    }
}
