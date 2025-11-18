package org.example.tests;

import static io.restassured.RestAssured.given;

import TestToFail.CreateNewBookingWithWrongSchema;
import TestToFail.DeleteCurrentBookingUnautherized;
import TestToFail.GetTokenWithBadCredintials;
import TestToFail.UpdateCurrentBookingNonAuthorized;
import TestToPass.*;
import io.restassured.response.Response;
import org.example.apis.*;
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
    CreateTokenPojo validBody = createTokenApi.getTokenBody("admin", "password123");
    public GetAllIds getAllIdsApi = new GetAllIds();
    public String firstId;
    Response response;
    String token;
    String newId;


    /****Tests To Pass***/
    @Test
    public void TokenCreation()
    {
        response = GetToken.getToken(validBody, url, createTokenApi);
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
        response = GetToken.getToken(validBody, url, createTokenApi);
        token = GetToken.tokenExtractor(response);
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.getId(response);
        response = UpdateCurrentBookingFully.updateCurrentBookingFully(url, newId, token);
    }
    @Test
    public void BookingPartiallyUpdate()
    {
        response = GetToken.getToken(validBody, url, createTokenApi);
        token = GetToken.tokenExtractor(response);
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.getId(response);
        response = UpdateCurrentBookingPartially.updateCurrentBookingPartially(url, newId, token);
    }
    @Test
    public void BookingDelete()
    {
        response = GetToken.getToken(validBody, url, createTokenApi);
        token = GetToken.tokenExtractor(response);
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.getId(response);
        response = DeleteCurrentBooking.deleteBooking(url,newId,token);
    }
    /***TestToFail***/
    @Test
    public void TokenWithWrongCredintialsInvalidUserName(){
        CreateTokenPojo invalidBodyWrongUserName = createTokenApi.getTokenBody("Mohamed", "password123");
        response = GetTokenWithBadCredintials.getToken(invalidBodyWrongUserName, url, createTokenApi);
    }
    @Test
    public void TokenWithWrongCredintialsInvalidPassword(){
        CreateTokenPojo invalidBodyWrongPassword = createTokenApi.getTokenBody("admin", "Mohamed");
        response = GetTokenWithBadCredintials.getToken(invalidBodyWrongPassword, url, createTokenApi);
    }
    @Test
    public void BookingCreationWithWrongSchema()
    {
        response = CreateNewBookingWithWrongSchema.createNewBookingNoFirstName(url);
    }
    @Test
    public void BookingCreationWithWrongCheckinCheckoutCompare()
    {
        response = CreateNewBookingWithWrongSchema.createNewBookingWithWrongCheckinCheckout(url);
    }
    @Test
    public void UpdateCurrentBookingFullyNonAutherized()
    {
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.getId(response);
        response = UpdateCurrentBookingNonAuthorized.updateCurrentBookingFully(url,newId);
    }
    @Test
    public void UpdateCurrentBookingPartiallyNonAutherized()
    {
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.getId(response);
        response = UpdateCurrentBookingNonAuthorized.updateCurrentBookingPartially(url,newId);
    }
    @Test
    public void DeteteCurrentBookingUnauthorized()
    {
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.getId(response);
        response = DeleteCurrentBookingUnautherized.deleteBookingUnauthorized(url,newId);
    }




}
