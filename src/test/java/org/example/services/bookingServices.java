package org.example.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.apis.*;
import org.example.base.BaseApi;
import pojo.BookingDatesPojo;
import pojo.CreateBookingPojo;
import utiles.LogsUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.not;
import static io.restassured.RestAssured.given;

public class bookingServices {
    /**
     * good scenarios
     **/
    @Step("Get All IDs in DataBase")
    public static Response getAllIdes(GetAllIds getAllIdsApi, String url) {
        String firstId;
        Response resp = given()
                .spec(BaseApi.getRequestSpec())
                .baseUri(url)

                .when()
                .get(getAllIdsApi.getEndPoint())

                .then()
                .statusCode(200)
                .extract().response();
        LogsUtils.info("All IDs is here");
        return resp;

    }

    @Step("Get first ID")
    public static String getFirstId(Response resp) {
        String firstId;
        firstId = resp.jsonPath().getString("[0].bookingid");
        LogsUtils.info("First ID is: " + firstId);
        return firstId;
    }

    @Step("Get information of Booking with specific ID:{id} ")
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
                        .extract().response();
        LogsUtils.info("Specific booking info");
        return resp;
    }

    @Step("Create New Booking")
    public static Response createNewBooking(String url, CreateBookingPojo bookingBody, CreateBooking createBooking) {
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
        LogsUtils.info("Booking Created");
        return resp;
    }

    @Step("Get the Booing ID")
    public static String getId(Response resp) {
        String bookingId;
        bookingId = resp.jsonPath().getString("bookingid");
        LogsUtils.info("The ID is: " + bookingId);

        return bookingId;
    }

    @Step("Fully Update the Booking with ID : {Id}")
    public static Response updateCurrentBookingFully(String url, String Id, String token, CreateBookingPojo updateBookingBody, UpdateBooking updateBooking) {

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
                        .extract().response();
        LogsUtils.info("booking with ID = " + Id + " is Updated");

        return resp;
    }

    @Step("Partially Update Booking with ID: {Id}")
    public static Response updateCurrentBookingPartially(String url, String Id, String token, Map<String, Object> partialUpdate, UpdateBooking updateBooking) {
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
                        .extract().response();
        LogsUtils.info("booking with ID = " + Id + " is Updated");

        return resp;
    }

    @Step("Delete the Booking with the ID: {Id}")
    public static Response deleteBooking(String url, String Id, String token, DeleteBooking deleteBooking) {
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .header("Cookie", "token=" + token)
                        .when()
                        .delete(deleteBooking.getEndPoint())
                        .then()
                        .statusCode(201)
                        .extract().response();
        LogsUtils.info("booking with ID = " + Id + "is Deleted");
        return resp;
    }

    /**
     * bad scenarios
     **/
    @Step("Create booking with no first name")
    public static Response createNewBookingNoFirstName(String url, CreateBookingPojo bookingBody, CreateBooking createBooking) {
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
        LogsUtils.info("Wrong Schema Missing argument");
        return resp;
    }

    @Step("Create booking checkin date is after checkout one")
    public static Response createNewBookingWithWrongCheckinCheckout(String url, CreateBookingPojo bookingBody, CreateBooking createBooking) {
        LocalDate checkin = LocalDate.parse(bookingBody.getBookingdates().getCheckin());
        LocalDate checkout = LocalDate.parse(bookingBody.getBookingdates().getCheckout());
        Response resp;
        if (checkin.isBefore(checkout)) {
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
            LogsUtils.info("Nothing wrong in the schema");
        } else {
            resp =
                    given()
                            .spec(BaseApi.getRequestSpec())
                            .baseUri(url)
                            .body(bookingBody)

                            .when()
                            .post(createBooking.getEndPoint())


                            .then()
                            .statusCode(not(200))
                            .extract().response();
            LogsUtils.info("Wrong Schema Checkin and checkout problem");
        }

        return resp;
    }

    @Step("Fully Update Booking without Authentication")
    public static Response updateCurrentBookingFullyWithNoAuthentication(String url, String Id, CreateBookingPojo updateBookingBody, UpdateBooking updateBooking) {
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
        LogsUtils.info("Not authorized Fully Update");
        return resp;
    }

    @Step("Partially Update Booking without Authentication")
    public static Response updateCurrentBookingPartiallyWithNoAuthentication(String url, String Id, Map<String, Object> partialUpdate, UpdateBooking updateBooking) {
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .body(partialUpdate)
                        .when()
                        .patch(updateBooking.getEndPoint())
                        .then()
                        .statusCode(403)
                        .extract().response();
        LogsUtils.info("Not authorized Partially Update");
        return resp;
    }

    @Step("Delete Booking without Authentication")
    public static Response deleteBookingUnauthorized(String url, String Id, DeleteBooking deleteBooking) {
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .when()
                        .delete(deleteBooking.getEndPoint())
                        .then()
                        .statusCode(403)
                        .extract().response();
        LogsUtils.info("Not authorized Delete");

        return resp;

    }

    @Step("Fully Update the Booking with ID : {Id} after Delete")
    public static Response updateCurrentBookingFullyAfterDelete(String url, String Id, String token, CreateBookingPojo updateBookingBody, UpdateBooking updateBooking) {

        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .header("Cookie", "token=" + token)
                        .body(updateBookingBody)

                        .when()
                        .put(updateBooking.getEndPoint())


                        .then()
                        .statusCode(405)
                        .extract().response();
        LogsUtils.info("Method not allowed Cant update a deleted one");

        return resp;
    }

    @Step("Partially Update Booking with ID: {Id}")
    public static Response updateCurrentBookingPartiallyAfterDelete(String url, String Id, String token, Map<String, Object> partialUpdate, UpdateBooking updateBooking) {
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .header("Cookie", "token=" + token)
                        .body(partialUpdate)
                        .when()
                        .patch(updateBooking.getEndPoint())
                        .then()
                        .statusCode(405)
                        .extract().response();
        LogsUtils.info("Method not allowed Cant update a deleted one");

        return resp;
    }

    @Step("Delete the Booking with the ID: {Id}")
    public static Response deleteBookingSecondTime(String url, String Id, String token, DeleteBooking deleteBooking) {
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .header("Cookie", "token=" + token)
                        .when()
                        .delete(deleteBooking.getEndPoint())
                        .then()
                        .statusCode(405)
                        .extract().response();
        LogsUtils.info("Cant delete a deleted Booking");
        return resp;
    }
}
