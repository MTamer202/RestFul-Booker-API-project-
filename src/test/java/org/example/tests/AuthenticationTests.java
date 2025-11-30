package org.example.tests;


import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.apis.CreateToken;
import org.example.base.BaseApi;
import org.example.services.authenticationServices;
import org.testng.annotations.Test;
import pojo.CreateTokenPojo;

public class AuthenticationTests extends BaseApi {

    @Test(description = "TC-AUTH-01: Login with valid credentials")
    @Story("Happy Scenario - Valid Login")
    @Description("Verify successful login with valid credentials")
    @Step("Put the value of the userName = admin and password = password123")
    public void TokenCreationValidLogin()
    {
        CreateToken createTokenApi = new CreateToken();
        CreateTokenPojo Body = createTokenApi.getTokenBody("admin", "password123");
        Response response = authenticationServices.getToken(Body,url,createTokenApi);
        String token = authenticationServices.tokenExtractor(response);
    }
    @Test(description = "TC-AUTH-02: Login with invalid username")
    @Story("Bad Scenario - Invalid Login")
    @Description("Verify bad credentials with wrong username")
    @Step("Put the value of the userName = mohamed and password = password123")
    public void TokenWithWrongCredintialsInvalidUserName(){
        CreateToken createTokenApi = new CreateToken();
        CreateTokenPojo Body = createTokenApi.getTokenBody("Mohamed", "password123");
        Response response = authenticationServices.getTokenWithBadCredintials(Body,url,createTokenApi);
    }
    @Test(description = "TC-AUTH-03: Login with invalid password")
    @Story("Bad Scenario - Invalid Login")
    @Description("Verify bad credentials with wrong Password")
    @Step("Put the value of the userName = admin and password = Mohamed")
    public void TokenWithWrongCredintialsInvalidPassword(){
        CreateToken createTokenApi = new CreateToken();
        CreateTokenPojo Body = createTokenApi.getTokenBody("admin", "Mohamed");
        Response response = authenticationServices.getTokenWithBadCredintials(Body,url, createTokenApi);
    }
    @Test(description = "TC-AUTH-04: Login with empty data")
    @Story("Bad Scenario - Invalid Login")
    @Description("Verify bad credentials with empty username and password")
    @Step("Put the value of the userName =  and password = ")
    public void TokenWithWrongCredintialsEmptyData(){
        CreateToken createTokenApi = new CreateToken();
        CreateTokenPojo Body = createTokenApi.getTokenBody("", "");
        Response response = authenticationServices.getTokenWithBadCredintials(Body,url, createTokenApi);
    }

}
