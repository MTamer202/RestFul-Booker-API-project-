package TestToPass;

import io.restassured.response.Response;
import org.example.apis.CreateBooking;
import org.example.base.BaseApi;
import pojo.BookingDatesPojo;
import pojo.CreateBookingPojo;

import static io.restassured.RestAssured.given;

public class CreateNewBooking {
    public static Response createNewBooking(String url) {
        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");

        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .body(bookingBody)

                        .when()
                        .post(createBooking.getEndPoint())


                        .then()
                        .statusCode(200)
                        .extract().response();
        return resp;

    }
    public static String getId(Response resp) {
        String bookingId;
        bookingId = resp.jsonPath().getString("bookingid");
        return bookingId;
    }

}
