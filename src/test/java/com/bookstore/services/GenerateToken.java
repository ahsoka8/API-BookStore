package com.bookstore.services;

import com.bookstore.utilities.Globals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;

public class GenerateToken extends Globals {
    public void generateToken(){

        String body="{\n" +
                "  \"userName\": \""+username+"\",\n" +
                "  \"password\": \""+password+"\"\n" +
                "}";

        response= RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body).log().all()
                .when()
                .post("/Account/v1/GenerateToken")
                .prettyPeek();
    }


    public void validateTokenIsGenerated(){
        //Assert status code
        Assert.assertEquals(200, response.statusCode());

        //get the token and assign it to a global variable
        token = response.path("token");

        //Assert that response body contains status and its value is Success
        Assert.assertEquals("Success", response.path("status"));
    }

}
