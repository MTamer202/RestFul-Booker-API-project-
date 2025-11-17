package org.example.tests;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.example.apis.*;
import org.example.base.BaseApi;
import org.testng.annotations.Test;
import pojo.CreateTokenPojo;

/******End to End Flow*****/
/*
* Five APIs with example for each:
* 1-Token creation(Post)
* 2-Get All Booking(Get)
* 3-Get Booking Info(Get)
* 4-Create Booking(Post)
* 5-Fully Update Booking(Put)
* 6-Partially Update Booking(Patch)
* 7-Delete Booking(delete)
*/
public class EndToEndFlow {
    public final String url = "https://restful-booker.herokuapp.com";
    public CreateToken createTokenApi = new CreateToken();
    CreateTokenPojo body = createTokenApi.getTokenBody("admin", "password123");
    public GetAllIds getAllIdsApi = new GetAllIds();
    public String firstId;
    Response response;
    String token;
    String newId;


    /****Tests To Pass***/ /***------> How to deal with bad tests or even assert using this**/
    @Test
    public void TokenCreation()
    {
        response = GetToken.getToken(body, url, createTokenApi);
        token = GetToken.tokenExtractor(response);
    }
    @Test
    public void getIdes() {
        response = GetAllIdes.getAllIdes(getAllIdsApi, url);
        firstId = GetAllIdes.getFirstId(response);
        response = GetBookingInfo.getBookingInfo(firstId, url);
    }
    @Test
    public void BookingCreation()
    {
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.getId(response);
    }
    @Test
    public void BookingFullyUpdate()
    {
        response = GetToken.getToken(body, url, createTokenApi);
        token = GetToken.tokenExtractor(response);
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.getId(response);
        response = UpdateCurrentBookingFully.updateCurrentBookingFully(url, newId, token);
    }
    @Test
    public void BookingPartiallyUpdate()
    {
        response = GetToken.getToken(body, url, createTokenApi);
        token = GetToken.tokenExtractor(response);
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.getId(response);
        response = UpdateCurrentBookingPartially.updateCurrentBookingPartially(url, newId, token);
    }
    @Test
    public void BookingDelete()
    {
        response = GetToken.getToken(body, url, createTokenApi);
        token = GetToken.tokenExtractor(response);
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.getId(response);
        response = DeleteCurrentBooking.deleteBooking(url,newId,token);
    }
}
