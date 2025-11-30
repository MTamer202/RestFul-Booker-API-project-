package org.example.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.apis.CreateToken;
import org.example.base.BaseApi;
import pojo.CreateTokenPojo;
import utiles.LogsUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

public class authenticationServices {

  /**Good Scenarios**/
    @Step("Create Token with valid inputs")
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
                        .body("token", notNullValue())
                        .extract()
                        .response();
        LogsUtils.info("Token is Created");
        return resp;
    }
    @Step("Extract token From response")
    public static String tokenExtractor(Response resp) {
        String token;
        token = resp.jsonPath().getString("token");
        LogsUtils.info("Token is: "+token);
        return token;
    }
    /**Bad Scenarios**/
    public static Response getTokenWithBadCredintials(CreateTokenPojo body, String url, CreateToken createTokenApi) {
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .body(body)

                        .when()
                        .post(createTokenApi.getEndPoint())

                        .then()
                        .statusCode(200)
                        .body("reason",containsString("Bad credentials"))
                        .extract()
                        .response();
        LogsUtils.info("Bad credentials");
        return resp;
    }
}


