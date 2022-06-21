package io.eeaters;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
@QuarkusTest
public class TokenResourceT {


    @Test
    public void rolesAllowed() {
        Response response = given()
                .auth()
                .oauth2(generateUserToken())
                .when()
                .get("/secured/roles-allowed").andReturn();
        System.out.println("response.statusCode() = " + response.statusCode());
    }


    private String generateUserToken() {
        int i = (int) (System.currentTimeMillis() / 1000);
        long expireTime = i + 60 * 60 * 24;
        String user = Jwt.upn("jdoe@quarkus.io")
                .issuer("https://example.com/issuer")
                .groups("User")
                .expiresIn(expireTime)
                .claim(Claims.birthdate.name(), "2022-06-21")
                .sign();

        System.out.println("user = " + user);
        return user;
    }

}
