//package io.eeaters;
//
//
//import io.eeaters.easy.delivery.entity.model.ChannelAccount;
//import io.quarkus.test.junit.QuarkusTest;
//import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.given;
//
//public class ChannelAccountResourceT extends GreetingResourceTest{
//
//    @Test
//    public void create() {
//        ChannelAccount channelAccount = new ChannelAccount();
//        channelAccount.setId(2L);
//        channelAccount.setChannel("shunfeng");
//        channelAccount.setAppKey("342");
//
//        System.out.println("channelAccount = " + channelAccount);
//        given()
//                .when()
//                .post("/channel/account/create")
//                .body()
//                .prettyPrint();
//
//    }
//
//
//}
