package TestToFail;

import io.restassured.response.Response;
import org.example.apis.DeleteBooking;
import org.example.base.BaseApi;

import static io.restassured.RestAssured.given;

public class DeleteCurrentBookingUnautherized {
    public static Response deleteBookingUnauthorized(String url, String Id) {
        DeleteBooking deleteBooking = new DeleteBooking(Id);
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .when()
                        .delete(deleteBooking.getEndPoint())
                        .then()
                        .statusCode(403)
                        .extract().response();
        return resp;

    }
}
