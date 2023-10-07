package com.bookstore.stepDefs;

import com.bookstore.services.AddNewUser;
import com.bookstore.services.AuthorizeUser;
import com.bookstore.services.GenerateToken;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Account_StepDefs {
    AddNewUser addNewUser = new AddNewUser();
    GenerateToken generateToken = new GenerateToken();

    AuthorizeUser authorizeUser= new AuthorizeUser();

    @When("Add new user")
    public void add_new_user() {
        addNewUser.addNewUser();
    }

    @Then("Validate that the new user is posted")
    public void validate_that_the_new_user_is_posted() {
        addNewUser.validateThatUserPosted();
    }


    @When("Generate Token")
    public void generateToken() {
        generateToken.generateToken();
    }

    @Then("Validate that the token is generated")
    public void validateThatTheTokenIsGenerated() {
        generateToken.validateTokenIsGenerated();
    }

    @When("Authorized User")
    public void authorizedUser() {
        authorizeUser.authorizeUser();
    }

    @Then("Validate that the user is authorized")
    public void validateThatTheUserIsAuthorized() {
        authorizeUser.validateThatUserIsAuthorized();
    }
}
