package io.eeaters;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void testPartnerInfo() {
        given()
                .when()
                .get("/hello/partnerInfo/1")
                .body()
                .prettyPrint();
    }

}