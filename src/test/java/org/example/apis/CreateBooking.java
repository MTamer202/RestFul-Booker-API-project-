package org.example.apis;

import pojo.BookingDatesPojo;
import pojo.CreateBookingPojo;
import pojo.CreateTokenPojo;

public class CreateBooking {
    private String endPoint = "/booking";
    private CreateBookingPojo bookingPojo = new CreateBookingPojo();

    public CreateBookingPojo getBookingBody(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDatesPojo bookingDates, String additionalneeds) {
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setAdditionalneeds(additionalneeds);
        bookingPojo.setBookingdates(bookingDates);
        return bookingPojo;
    }

    public CreateBookingPojo getBookingBodyWrongSchemaNoFirstName(String lastname, int totalprice, boolean depositpaid, BookingDatesPojo bookingDates, String additionalneeds) {
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setAdditionalneeds(additionalneeds);
        bookingPojo.setBookingdates(bookingDates);
        return bookingPojo;
    }

    public String getEndPoint() {
        return endPoint;
    }


}

