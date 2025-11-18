package TestToPass;

import io.restassured.response.Response;
import org.example.apis.UpdateBooking;
import org.example.base.BaseApi;
import pojo.BookingDatesPojo;
import pojo.CreateBookingPojo;

import static io.restassured.RestAssured.given;

public class UpdateCurrentBookingFully {
    public static Response updateCurrentBookingFully(String url, String Id, String token) {
        UpdateBooking updateBooking = new UpdateBooking(Id);
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo updateBookingBody = updateBooking.getBookingBody("Tamer", "Mohamed", 100500, true, bookingDates, "HAHAHAHAHAAH");
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .header("Cookie", "token=" + token)
                        .body(updateBookingBody)

                        .when()
                        .put(updateBooking.getEndPoint())


                        .then()
                        .statusCode(200)
                        .log().body().extract().response();
        return resp;
    }
}
