package com.bookstore.services;

import com.bookstore.utilities.Globals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class AuthorizeUser extends Globals {

    public void authorizeUser() {
        String body = """
                {
                  "userName": "%s",
                  "password": "%s"
                }""".formatted(username, password);

        response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body).log().all()
                .when()
                .post("/Account/v1/Authorized")
                .prettyPeek();
    }

    public void validateThatUserIsAuthorized(){
        //assert status code
        Assert.assertEquals(200, response.statusCode());

        //validate response body
        Assert.assertTrue(response.body().asString().equals("true"));
    }


}
