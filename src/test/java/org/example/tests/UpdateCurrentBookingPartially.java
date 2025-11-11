package org.example.tests;

import io.restassured.response.Response;
import org.example.apis.UpdateBooking;
import org.example.base.BaseApi;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateCurrentBookingPartially {
    public static Response updateCurrentBookingPartially(String url, String Id, String token) {
        UpdateBooking updateBooking = new UpdateBooking(Id);

        Map<String, Object> partialUpdate = new HashMap<>();
        partialUpdate.put("firstname", "Mazen");
        partialUpdate.put("lastname", "Ehab");
        partialUpdate.put("totalprice", 2000);
        System.out.println("Token: " + token);
        System.out.println("Endpoint: " + updateBooking.getEndPoint());
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .header("Cookie", "token=" + token)
                        .body(partialUpdate)
                        .when()
                        .patch(updateBooking.getEndPoint())
                        .then()
                        .statusCode(200)
                        .log().body().extract().response();
        return resp;
    }
}
