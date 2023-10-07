package com.bookstore.services;

import com.bookstore.utilities.Globals;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class AddNewUser extends Globals {

    Faker faker = new Faker();

    public void addNewUser() {
        username = faker.name().username();
        password = "Ab1*"+faker.internet().password(8, 16, true, true, true);

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("userName", username);
        bodyMap.put("password", password);

        response= RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when().log().all()
                .post("/Account/v1/User")
                .prettyPeek();
    }


    public void validateThatUserPosted() {
        //assert status code
        Assert.assertEquals(201, response.statusCode());

        //get userId from body
        userId = response.path("userID");

        //assert that username is correct
        Assert.assertEquals(username, response.path("username"));
    }

}
