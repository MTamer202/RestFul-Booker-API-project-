package org.example.tests;

import io.restassured.response.Response;
import org.example.apis.GetBookingId;
import org.example.base.BaseApi;

import static io.restassured.RestAssured.given;

public class GetBookingInfo {
    public static Response getBookingInfo(String id, String url) {
        GetBookingId testId = new GetBookingId(id);
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .when()
                        .get(testId.getEndPoint())

                        .then()
                        .statusCode(200)
                        .log().body().extract().response();
        return resp;
    }
}
