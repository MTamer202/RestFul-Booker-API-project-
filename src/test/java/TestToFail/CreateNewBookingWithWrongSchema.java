package TestToFail;

import io.restassured.response.Response;
import org.example.apis.CreateBooking;
import org.example.base.BaseApi;
import pojo.BookingDatesPojo;
import pojo.CreateBookingPojo;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

public class CreateNewBookingWithWrongSchema {
    public static Response createNewBookingNoFirstName(String url) {
        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBodyWrongSchema("Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .body(bookingBody)

                        .when()
                        .post(createBooking.getEndPoint())


                        .then()
                        .statusCode(500)
                        .extract().response();
        return resp;
    }

    public static Response createNewBookingWithWrongCheckinCheckout(String url) {
        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2024-10-15");

        LocalDate checkin = LocalDate.parse(bookingDates.getCheckin());
        LocalDate checkout = LocalDate.parse(bookingDates.getCheckout());
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        Response resp ;

        if (checkin.isBefore(checkout))
        {
             resp =
                    given()
                            .spec(BaseApi.getRequestSpec())
                            .baseUri(url)
                            .body(bookingBody)

                            .when()
                            .post(createBooking.getEndPoint())


                            .then()
                            .statusCode(200)
                            .extract().response();
        }
        else {
             resp =
                    given()
                            .spec(BaseApi.getRequestSpec())
                            .baseUri(url)
                            .body(bookingBody)

                            .when()
                            .post(createBooking.getEndPoint())


                            .then()
                            .statusCode(500)
                           .extract().response();
        }
        return resp;
    }
}
