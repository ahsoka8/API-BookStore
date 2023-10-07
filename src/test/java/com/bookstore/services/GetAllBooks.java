package com.bookstore.services;

import com.bookstore.utilities.Globals;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class GetAllBooks extends Globals {


    public void getAllBooks(){
     response = RestAssured.given().accept(ContentType.JSON)
                .when().log().all()
                .get("/BookStore/v1/Books")
                .prettyPeek();
    }

    public void validateThatAllBooksAreListed(){
        //assert status code
        Assert.assertEquals(200, response.statusCode());

        //get the data from body
       isbnNumbers= response.path("books.isbn");

       //assert that all isbn numbers are not null
        isbnNumbers.forEach(number -> Assert.assertNotNull(number));
        //isbnNumbers.forEach(Assert::assertNotNull);
    }


}
