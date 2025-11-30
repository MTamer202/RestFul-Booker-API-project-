package org.example.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import utiles.AllureUtils;
import utiles.FilesUtils;

import java.io.File;

public class BaseApi {
    public static final String url = "https://restful-booker.herokuapp.com";
    File allure_results = new File("test-outputs/allure-results");

    @BeforeSuite
    public void beforeSuite(){
        FilesUtils.deleteFiles(allure_results);
    }

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                addHeader("Accept","application/json").
                build();
    }

}
