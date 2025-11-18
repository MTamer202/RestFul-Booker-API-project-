package TestToPass;

import io.restassured.response.Response;
import org.example.apis.DeleteBooking;
import org.example.base.BaseApi;

import static io.restassured.RestAssured.given;

public class DeleteCurrentBooking {
    public static Response deleteBooking(String url, String Id, String token) {
        DeleteBooking deleteBooking = new DeleteBooking(Id);
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .header("Cookie", "token=" + token)
                        .when()
                        .delete(deleteBooking.getEndPoint())
                        .then()
                        .statusCode(201)
                        .log().body().extract().response();
        System.out.println("Endpoint: " + deleteBooking.getEndPoint());
        return resp;

    }
}
