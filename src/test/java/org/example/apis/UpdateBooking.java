package org.example.apis;

import pojo.BookingDatesPojo;
import pojo.CreateBookingPojo;

public class UpdateBooking
{
    private String endPoint ;
public UpdateBooking(String bookingId)
{
    endPoint = "/booking/"+bookingId;
}
    private CreateBookingPojo updateBookingPojo = new CreateBookingPojo();

    public  CreateBookingPojo getBookingBody(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDatesPojo bookingDates , String additionalneeds) {
        updateBookingPojo.setFirstname(firstname);
        updateBookingPojo.setLastname(lastname);
        updateBookingPojo.setTotalprice(totalprice);
        updateBookingPojo.setDepositpaid(depositpaid);
        updateBookingPojo.setAdditionalneeds(additionalneeds);
        updateBookingPojo.setBookingdates(bookingDates);
        return updateBookingPojo;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
