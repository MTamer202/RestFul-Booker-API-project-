package org.example.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.openqa.selenium.devtools.v85.network.model.Request;

public class BaseApi {

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                addHeader("Accept","application/json").
                build();
    }
}
