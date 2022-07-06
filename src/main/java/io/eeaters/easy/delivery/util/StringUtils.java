package io.eeaters.easy.delivery.util;

public interface StringUtils {

    static boolean hasText(CharSequence str) {
        return (str != null && str.length() > 0 && !"null".equals(str) && containsText(str));
    }

    static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
