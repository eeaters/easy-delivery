package io.eeaters.easy.delivery.util;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import org.eclipse.microprofile.jwt.Claims;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public interface TokenUtils {


    // -Dexec.mainClass=io.eeaters.easy.delivery.util.TokenUtils -Dexec.classpathScope=test
    static void main(String[] args) throws Exception {
        System.setProperty("smallrye.jwt.sign.key.location", "secured/privateKey.pem");
        int i = currentTimeInSecs();
        long expireTime = i + 60 * 60 * 24;
        String claims = Jwt.upn("jdoe@quarkus.io")
                .issuer("https://example.com/issuer")
                .groups("User")
                .claim(Claims.birthdate.name(), "2022-06-21")
                .expiresAt(expireTime)
                .sign();

        String s = TokenUtils.generateTokenString(claims, new HashMap<>());
        System.out.println("s = " + s);
    }


    static String generateTokenString(String jsonRes, Map<String, Long> timeClaims) throws Exception {

        PrivateKey privateKey = readPrivateKey("/secured/privateKey.pem");
        JwtClaimsBuilder claims = Jwt.claims(jsonRes);
        int currentTimeInSecs = currentTimeInSecs();
        long exp = (timeClaims != null && timeClaims.containsKey(Claims.exp.name())) ? timeClaims.get(Claims.exp.name()) : currentTimeInSecs + 300;
        claims.issuedAt(currentTimeInSecs);
        claims.expiresAt(exp);
        claims.claim(Claims.auth_time.name(), currentTimeInSecs);
        return claims.jws().keyId(jsonRes).sign(privateKey);
    }


    static PrivateKey readPrivateKey(final String pemResNames) throws Exception {
        try (InputStream inputStream = TokenUtils.class.getResourceAsStream(pemResNames)) {
            byte[] temp = new byte[4096];
            int length = inputStream.read(temp);
            String pemEncoded = new String(temp, 0, length, "UTF-8");
            String normalizedPem = removeBeginEnd(pemEncoded);
            byte[] pemBytes = Base64.getDecoder().decode(normalizedPem);

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pemBytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(keySpec);
        }
    }


    private static String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }

    private  static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }
}
