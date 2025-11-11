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

    @Test
    public void getTokenTest() {
        response = GetToken.getToken(body, url, createTokenApi);
        token = GetToken.tokenExtractor(response);
        System.out.print("The token is:" + token);
    }

    @Test
    public void getAllIdesTest() {
        response = GetAllIdes.getAllIdes(getAllIdsApi, url);
    }

    @Test(dependsOnMethods = {"getAllIdesTest"})
    public void getFirstIdTest() {
        firstId = GetAllIdes.getFirstId(response);
        System.out.print("The First ID is: " + firstId);
    }

    @Test(dependsOnMethods = {"getFirstIdTest", "getAllIdesTest"})
    public void getBookingIdTest() {
        response = GetBookingInfo.getBookingInfo(firstId, url);
    }

    @Test
    public void CreateBookingTest() {
        String newId;
        response = CreateNewBooking.createNewBooking(url);
        newId = CreateNewBooking.geId(response);
        firstId = newId;
        System.out.print("The new ID is: " + newId);
    }

    @Test(dependsOnMethods = {"getTokenTest", "getAllIdesTest", "CreateBookingTest"})
    public void fullyUpdateBookingTest() {
        response = UpdateCurrentBookingFully.updateCurrentBookingFully(url, firstId, token);
    }

    @Test(dependsOnMethods = {"getTokenTest", "getAllIdesTest", "CreateBookingTest"})
    public void partialUpdateBookingTest() {
        response = UpdateCurrentBookingPartially.updateCurrentBookingPartially(url, firstId, token);
    }

    @Test(dependsOnMethods = {"getTokenTest","getAllIdesTest","getFirstIdTest"})
    public void deleteBooking() {
        response = DeleteCurrentBooking.deleteBooking(url,firstId,token);
    }
}
