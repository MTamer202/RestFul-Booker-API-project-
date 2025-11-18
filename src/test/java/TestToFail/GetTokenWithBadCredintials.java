package TestToFail;

import io.restassured.response.Response;
import org.example.apis.CreateToken;
import org.example.base.BaseApi;
import pojo.CreateTokenPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

public class GetTokenWithBadCredintials {

    public static Response getToken(CreateTokenPojo body, String url, CreateToken createTokenApi) {
        Response resp =
                given()
                        .spec(BaseApi.getRequestSpec())
                        .baseUri(url)
                        .body(body)

                        .when()
                        .post(createTokenApi.getEndPoint())

                        .then()
                        .statusCode(200)
                        .body("reason",containsString("Bad credentials") )
                        .extract()
                        .response();
        return resp;
    }
}
