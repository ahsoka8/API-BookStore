package com.bookstore.services;

import com.bookstore.utilities.Globals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;

public class UpdateBook extends Globals {


    public void updateBook() {

        String body = """
                {
                  "userId": "%s",
                  "isbn": "%s"
                }""".formatted(userId, isbnNumbers.get(1));

        response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("isbn", isbnNumbers.get(0))
                .and()
                .body(body)
                .when().log().all()
                .put("/BookStore/v1/Books/{isbn}")
                .prettyPeek();
    }


    public void validateThatTheBookIsUpdated() {
        //validate status code
        Assert.assertEquals(200, response.statusCode());

        //validate userId, usernam and isbn number
        Assert.assertEquals(userId, response.path("userId"));
        Assert.assertEquals(username, response.path("username"));
        Assert.assertEquals(isbnNumbers.get(1), response.path("books.isbn[0]"));
    }


}
