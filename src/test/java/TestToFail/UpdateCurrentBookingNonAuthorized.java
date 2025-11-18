package TestToFail;

import io.restassured.response.Response;
import org.example.apis.UpdateBooking;
import org.example.base.BaseApi;
import pojo.BookingDatesPojo;
import pojo.CreateBookingPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateCurrentBookingNonAuthorized {
    public static Response updateCurrentBookingFully(String url, String Id) {
        UpdateBooking updateBooking = new UpdateBooking(Id);
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo updateBookingBody = updateBooking.getBookingBody("Tamer", "Mohamed", 100500, true, bookingDates, "HAHAHAHAHAAH");
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .body(updateBookingBody)

                        .when()
                        .put(updateBooking.getEndPoint())


                        .then()
                        .statusCode(403)
                        .extract().response();
        return resp;
    }

    public static Response updateCurrentBookingPartially(String url, String Id) {
        UpdateBooking updateBooking = new UpdateBooking(Id);
        Map<String, Object> partialUpdate = new HashMap<>();
        partialUpdate.put("firstname", "Mazen");
        partialUpdate.put("lastname", "Ehab");
        partialUpdate.put("totalprice", 2000);
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .body(partialUpdate)
                        .when()
                        .patch(updateBooking.getEndPoint())
                        .then()
                        .statusCode(403)
                        .log().body().extract().response();
        return resp;
    }
}
