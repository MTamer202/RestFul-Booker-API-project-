package org.example.apis;

import pojo.CreateTokenPojo;

public class GetBookingId {
    private String endPoint;

    public GetBookingId(String bookingId) {
        endPoint = "/booking/" + bookingId;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
