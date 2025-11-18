package TestToPass;

import io.restassured.response.Response;
import org.example.apis.GetAllIds;
import org.example.base.BaseApi;

import static io.restassured.RestAssured.given;

public class GetAllIdes {
    public static Response getAllIdes(GetAllIds getAllIdsApi, String url) {
        String firstId;
        Response resp = given()
                .spec(BaseApi.getRequestSpec())
                .baseUri(url)

                .when()
                .get(getAllIdsApi.getEndPoint())

                .then()
                .statusCode(200)
                .log().body().extract().response();
        return resp;

    }

    public static String getFirstId(Response resp) {
        String firstId;
        firstId = resp.jsonPath().getString("[0].bookingid");
        return firstId;
    }
}
