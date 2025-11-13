package org.example.tests;

import io.restassured.response.Response;
import org.example.apis.CreateToken;
import org.example.base.BaseApi;
import pojo.CreateTokenPojo;

import static io.restassured.RestAssured.given;

public class GetToken {

    public static Response getToken(CreateTokenPojo body, String url, CreateToken createTokenApi) {
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .body(body)

                        .when()
                        .post(createTokenApi.getEndPoint())

                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract()
                        .response();
        //createTokenApi.setToken(resp.jsonPath().get("token"));
        return resp;
    }

    public static String tokenExtractor(Response resp) {
        String token;
        token = resp.jsonPath().getString("token");
        return token;

    }
}
