package org.example.tests;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.example.apis.*;
import org.example.base.BaseApi;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import pojo.BookingDatesPojo;
import pojo.CreateBookingPojo;
import pojo.CreateTokenPojo;
import java.util.Map;
import java.util.HashMap;

public class EndToEndFlow {
    private final String url = "https://restful-booker.herokuapp.com" ;
    public CreateToken createTokenApi = new CreateToken();
    CreateTokenPojo body = createTokenApi.getTokenBody("admin","password123");
    public GetAllIds getAllIdsApi = new GetAllIds();
    public String firstId;

@Test
    public void getToken(){
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .body(body)

                        .when()
                        .post(createTokenApi.getEndPoint())

                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract()
                        .response();
createTokenApi.setToken(resp.jsonPath().get("token"));
System.out.print(createTokenApi.getToken());
    }

    @Test

    public void getAllIdes()
    {
        Response resp = given()
                .spec(BaseApi.getRequestSpec())
                .baseUri(url)

                .when()
                .get(getAllIdsApi.getEndPoint())

                .then()
                .statusCode(200)
                .log().body().extract().response();
        firstId = resp.jsonPath().getString("[0].bookingid");
        System.out.print(firstId);
    }

@Test
    public void getBookingId()
    {
        GetBookingId testId = new GetBookingId(firstId);
        given()
                .spec(BaseApi.getRequestSpec())
                .baseUri(url)
                .when()
                .get(testId.getEndPoint())

                .then()
                .statusCode(200)
                .log().body().extract().response();

    }
    @Test
public void CreateBooking()
    {
        CreateBooking createBooking = new CreateBooking();
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");

        CreateBookingPojo bookingBody = createBooking.getBookingBody("Mohamed","Tamer",1000,true,bookingDates,"HAHAHAHAHAAH");

        given()
                .spec(BaseApi.getRequestSpec())
                .baseUri(url)
                .body(bookingBody)

                .when()
                .post(createBooking.getEndPoint())


                .then()
                .statusCode(200)
                .log().body().extract().response();

    }
    @Test(dependsOnMethods = {"getAllIdes"})
    public void UpdateBooking()
    {
        UpdateBooking updateBooking = new UpdateBooking(firstId);
        BookingDatesPojo bookingDates = new BookingDatesPojo("2025-10-09", "2025-10-15");

        CreateBookingPojo updateBookingBody = updateBooking.getBookingBody("Mohamed","Tamer",1000,true,bookingDates,"HAHAHAHAHAAH");

        given()
                .spec(BaseApi.getRequestSpec())
                .baseUri(url)
                .auth().preemptive().basic("admin", "password123")  // 👈 add credentials
                .body(updateBookingBody)

                .when()
                .put(updateBooking.getEndPoint())


                .then()
                .statusCode(200)
                .log().body().extract().response();



    }
    @Test(dependsOnMethods = {"getAllIdes"})
    public void partialUpdateBooking() {
        UpdateBooking updateBooking = new UpdateBooking(firstId);

        Map<String, Object> partialUpdate = new HashMap<>();
        partialUpdate.put("firstname", "Mohamed");
        partialUpdate.put("lastname", "Tamer");
        partialUpdate.put("totalprice", 1500);

        given()
                .spec(BaseApi.getRequestSpec())
                .baseUri(url)
                .auth().preemptive().basic("admin", "password123")
                .body(partialUpdate)
                .when()
                .patch(updateBooking.getEndPoint())
                .then()
                .statusCode(200)
                .log().body().extract().response();
    }
    @Test(dependsOnMethods = {"getAllIdes","UpdateBooking","partialUpdateBooking"})
    public void deleteBooking(){
    DeleteBooking deleteBooking = new DeleteBooking(firstId);


    given()
            .spec(BaseApi.getRequestSpec())
            .baseUri(url)
            .auth().preemptive().basic("admin", "password123")
            .when()
            .delete(deleteBooking.getEndPoint())
    .then()
            .statusCode(201)
            .log().body().extract().response();
    System.out.println("Endpoint: " + deleteBooking.getEndPoint());

}


}
