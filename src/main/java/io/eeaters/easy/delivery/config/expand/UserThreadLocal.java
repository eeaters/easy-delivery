package io.eeaters.easy.delivery.config.expand;

import io.eeaters.easy.delivery.entity.model.User;

import java.util.Optional;

public class UserThreadLocal {

    private static ThreadLocal<UserInfo> USER_INFO = new ThreadLocal();

    public static void remove() {
        USER_INFO.remove();
    }

    public static void setUserInfo(String token, User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setToken(token);
        USER_INFO.set(userInfo);
    }

    public static User getUser() {
        return Optional.ofNullable(USER_INFO.get())
                .map(UserInfo::getUser)
                .get();
    }

    public static String getToken() {
        return Optional.ofNullable(USER_INFO.get())
                .map(UserInfo::getToken)
                .get();
    }



    public static class UserInfo{
        private String token;
        private User user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }


}
