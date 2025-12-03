package org.example.tests;


import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.apis.*;
import org.example.base.BaseApi;
import org.example.services.authenticationServices;
import org.example.services.bookingServices;
import org.testng.annotations.Test;
import pojo.BookingDatesPojo;
import pojo.CreateBookingPojo;
import pojo.CreateTokenPojo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class BookingTests extends BaseApi {

    private Response response;
    private String BookingID;

    /**
     * Good Scenarios
     **/
    @Test(description = "TC-Booking-01: get all IDs")
    @Story("Happy Scenario - Get All Booking IDs")
    @Description("Verify All IDs are viewed")
    public void getIdes() {
        GetAllIds getAllIdsApi = new GetAllIds();
        response = bookingServices.getAllIdes(getAllIdsApi, url);
    }

    @Test(description = "TC-Booking-02: get Specific booking using ID")
    @Story("Happy Scenario - Get Specific Booking using ID")
    @Description("Verify The Booking with the ID is shown")
    @Step("Put the value of the first ID")
    public void getSpecificBookingInfo() {
        BookingID = bookingServices.getFirstId(response);
        response = bookingServices.getBookingInfo(BookingID, url);
    }

    @Test(description = "TC-Booking-03: Create New Booking and git its ID")
    @Story("Happy Scenario - Create New Booking")
    @Description("Verify The Booking is created and get its Id search for it")
    @Step("Create the booking and give it the good schema data")
    public void BookingCreation() {
        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        Response response = bookingServices.createNewBooking(url, bookingBody, createBooking);
        BookingID = bookingServices.getId(response);
    }

    @Test(description = "TC-Booking-04: Fully update Booking using the ID and authentication Token")
    @Story("Happy Scenario - Update Booking")
    @Description("Verify The Booking is updated")
    public void BookingFullyUpdate() {
        CreateToken createTokenApi = new CreateToken();
        CreateTokenPojo Body = createTokenApi.getTokenBody("admin", "password123");
        Response response = authenticationServices.getToken(Body, url, createTokenApi);
        String token = authenticationServices.tokenExtractor(response);

        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBooking(url, bookingBody, createBooking);
        BookingID = bookingServices.getId(response);

        UpdateBooking updateBooking = new UpdateBooking(BookingID);
        BookingDatesPojo bookingUpdatesDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo updateBookingBody = updateBooking.getBookingBody("Tamer", "Mohamed", 100500, true, bookingUpdatesDates, "HAHAHAHAHAAH");
        response = bookingServices.updateCurrentBookingFully(url, BookingID, token, updateBookingBody, updateBooking);
    }

    @Test(description = "TC-Booking-05: Partially update Booking using the ID and authentication Token")
    @Story("Happy Scenario - Update Booking")
    @Description("Verify The Booking is updated")
    public void BookingPartiallyUpdate() {
        CreateToken createTokenApi = new CreateToken();
        CreateTokenPojo Body = createTokenApi.getTokenBody("admin", "password123");
        Response response = authenticationServices.getToken(Body, url, createTokenApi);
        String token = authenticationServices.tokenExtractor(response);

        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBooking(url, bookingBody, createBooking);
        BookingID = bookingServices.getId(response);

        UpdateBooking updateBooking = new UpdateBooking(BookingID);
        Map<String, Object> partialUpdate = new HashMap<>();
        partialUpdate.put("firstname", "Mazen");
        partialUpdate.put("lastname", "Ehab");
        partialUpdate.put("totalprice", 2000);
        response = bookingServices.updateCurrentBookingPartially(url, BookingID, token, partialUpdate, updateBooking);
    }

    @Test(description = "TC-Booking-06: Delete Booking using the ID and authentication Token")
    @Story("Happy Scenario - Delete Booking")
    @Description("Verify The Booking is Deleted")
    public void BookingDelete() {

        CreateToken createTokenApi = new CreateToken();
        CreateTokenPojo Body = createTokenApi.getTokenBody("admin", "password123");
        Response response = authenticationServices.getToken(Body, url, createTokenApi);
        String token = authenticationServices.tokenExtractor(response);

        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBooking(url, bookingBody, createBooking);
        BookingID = bookingServices.getId(response);

        DeleteBooking deleteBooking = new DeleteBooking(BookingID);
        response = bookingServices.deleteBooking(url, BookingID, token, deleteBooking);
    }

    /**
     * Bad Scenarios
     **/
    @Test(description = "TC-Booking-07: Create New Booking with wrong schema")
    @Story("bad Scenario - Create New Booking wrong schema")
    @Description("there is no firstname in the schema")
    public void BookingCreationWithWrongSchemaNoFirstName() {
        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBodyWrongSchemaNoFirstName("Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBookingNoFirstName(url, bookingBody, createBooking);
    }

    @Test(description = "TC-Booking-08: Create New Booking with wrong schema")
    @Story("bad Scenario - Create New Booking wrong schema")
    @Description("checkin and checkout timing in the schema")
    public void BookingCreationWithWrongCheckinCheckoutCompare() {
        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2024-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBookingWithWrongCheckinCheckout(url, bookingBody, createBooking);
    }

    @Test(description = "TC-Booking-09:Fully Update Booking without authentication")
    @Story("bad Scenario -Update Booking Without authentication")
    @Description("verify that its forbidden")
    public void UpdateCurrentBookingFullyNonAutherized() {
        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBooking(url, bookingBody, createBooking);
        BookingID = bookingServices.getId(response);

        UpdateBooking updateBooking = new UpdateBooking(BookingID);
        BookingDatesPojo bookingUpdatesDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo updateBookingBody = updateBooking.getBookingBody("Tamer", "Mohamed", 100500, true, bookingUpdatesDates, "HAHAHAHAHAAH");
        response = bookingServices.updateCurrentBookingFullyWithNoAuthentication(url, BookingID, updateBookingBody, updateBooking);


    }

    @Test(description = "TC-Booking-10:partially Update Booking without authentication")
    @Story("bad Scenario -Update Booking Without authentication")
    @Description("verify that its forbidden")
    public void UpdateCurrentBookingPartiallyNonAutherized() {
        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBooking(url, bookingBody, createBooking);
        BookingID = bookingServices.getId(response);

        UpdateBooking updateBooking = new UpdateBooking(BookingID);
        Map<String, Object> partialUpdate = new HashMap<>();
        partialUpdate.put("firstname", "Mazen");
        partialUpdate.put("lastname", "Ehab");
        partialUpdate.put("totalprice", 2000);
        response = bookingServices.updateCurrentBookingPartiallyWithNoAuthentication(url, BookingID, partialUpdate, updateBooking);
    }

    @Test(description = "TC-Booking-11:Delete Booking without authentication")
    @Story("bad Scenario -Update Booking Without authentication")
    @Description("verify that its forbidden")
    public void DeteteCurrentBookingUnauthorized() {
        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBooking(url, bookingBody, createBooking);
        BookingID = bookingServices.getId(response);

        DeleteBooking deleteBooking = new DeleteBooking(BookingID);
        response = bookingServices.deleteBookingUnauthorized(url, BookingID, deleteBooking);

    }

    @Test(description = "TC-Booking-12: Fully Update a Deleted Booking using the ID and authentication Token")
    @Story("bad Scenario - Delete Booking")
    @Description("Verify The Booking is Deleted")
    public void BookingFullUpdateAfterDelete() {

        CreateToken createTokenApi = new CreateToken();
        CreateTokenPojo Body = createTokenApi.getTokenBody("admin", "password123");
        Response response = authenticationServices.getToken(Body, url, createTokenApi);
        String token = authenticationServices.tokenExtractor(response);

        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBooking(url, bookingBody, createBooking);
        BookingID = bookingServices.getId(response);

        UpdateBooking updateBooking = new UpdateBooking(BookingID);
        BookingDatesPojo bookingUpdatesDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo updateBookingBody = updateBooking.getBookingBody("Tamer", "Mohamed", 100500, true, bookingUpdatesDates, "HAHAHAHAHAAH");

        DeleteBooking deleteBooking = new DeleteBooking(BookingID);
        response = bookingServices.deleteBooking(url, BookingID, token, deleteBooking);
        response = bookingServices.updateCurrentBookingFullyAfterDelete(url, BookingID, token, updateBookingBody, updateBooking);
    }

    @Test(description = "TC-Booking-13: paritally Update a Deleted Booking using the ID and authentication Token")
    @Story("bad Scenario - Delete Booking")
    @Description("Verify The Booking is Deleted")
    public void BookingPartialUpdateAfterDelete() {

        CreateToken createTokenApi = new CreateToken();
        CreateTokenPojo Body = createTokenApi.getTokenBody("admin", "password123");
        Response response = authenticationServices.getToken(Body, url, createTokenApi);
        String token = authenticationServices.tokenExtractor(response);

        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBooking(url, bookingBody, createBooking);
        BookingID = bookingServices.getId(response);

        UpdateBooking updateBooking = new UpdateBooking(BookingID);
        Map<String, Object> partialUpdate = new HashMap<>();
        partialUpdate.put("firstname", "Mazen");
        partialUpdate.put("lastname", "Ehab");
        partialUpdate.put("totalprice", 2000);

        DeleteBooking deleteBooking = new DeleteBooking(BookingID);
        response = bookingServices.deleteBooking(url, BookingID, token, deleteBooking);
        response = bookingServices.updateCurrentBookingPartiallyAfterDelete(url, BookingID, token, partialUpdate, updateBooking);
    }


    @Test(description = "TC-Booking-14: Delete a Deleted Booking using the ID and authentication Token")
    @Story("bad Scenario - Delete Booking")
    @Description("Verify The Booking is Deleted")
    public void BookingDeleteTwoTimes() {

        CreateToken createTokenApi = new CreateToken();
        CreateTokenPojo Body = createTokenApi.getTokenBody("admin", "password123");
        Response response = authenticationServices.getToken(Body, url, createTokenApi);
        String token = authenticationServices.tokenExtractor(response);

        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");
        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed", "Tamer", 1000, true, bookingDates, "HAHAHAHAHAAH");
        response = bookingServices.createNewBooking(url, bookingBody, createBooking);
        BookingID = bookingServices.getId(response);

        DeleteBooking deleteBooking = new DeleteBooking(BookingID);
        response = bookingServices.deleteBooking(url, BookingID, token, deleteBooking);
        response = bookingServices.deleteBookingSecondTime(url, BookingID, token, deleteBooking);
    }


}

/*
 * presentation:
 * API Introduction as a technology.
 * why API testing.
 * Tools (postman and rest assured why).
 * screen manual Bug and manual video postman and rest assured.
 * challenges I faced in the project.
 * the Code link.
 */

