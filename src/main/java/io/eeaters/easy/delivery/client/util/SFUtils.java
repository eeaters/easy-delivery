package io.eeaters.easy.delivery.client.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.MessageDigest;
import java.util.Base64;

public class SFUtils {


    static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String getSign(T obj) throws Exception {
        String dataInfo = objectMapper.writeValueAsString(obj) ;
        String appId = "test";
        String appKey = "40317e36fe41a8093d273754a0919e41";

        String originStr = dataInfo + "&" + appId + "&" + appKey;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(originStr.getBytes());
        byte[] digest = md5.digest();

        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < digest.length; i++) {
            int di = digest[i];
            if (di < 0) {
                i += 256;
            }
            if (i < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(i));
        }
        return Base64.getEncoder().encodeToString(sb.toString().getBytes());
    }

}
