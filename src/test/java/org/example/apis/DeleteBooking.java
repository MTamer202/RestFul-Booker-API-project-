package org.example.apis;

import pojo.BookingDatesPojo;
import pojo.CreateBookingPojo;

public class DeleteBooking {
    private String endPoint = "/booking/";

    public DeleteBooking(String bookingId) {
        endPoint = endPoint + bookingId;
    }

    public String getEndPoint() {
        return endPoint;

    }
}