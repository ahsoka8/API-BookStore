package com.bookstore.services;

import com.bookstore.utilities.Globals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class AssignNewBook extends Globals {
    public void assignNewBook() {

        String body = """
                {
                  "userId": "%s",
                  "collectionOfIsbns": [
                    {
                      "isbn": "%s"
                    }
                  ]
                }""".formatted(userId, isbnNumbers.get(0));

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .and()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when().log().all()
                .post("/BookStore/v1/Books")
                .prettyPeek();
    }

    public void validateThatBookIsAssigned() {
        //Assert status code
        Assert.assertEquals(201, response.statusCode());

        //validate that Isbn number is correct
        String actualIsbn = response.path("books.isbn[0]");
        String expectedIsbn = isbnNumbers.get(0);
        Assert.assertEquals(expectedIsbn, actualIsbn);

    }


}
