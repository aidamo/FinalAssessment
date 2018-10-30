package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class MyStepdefs {

    private RequestSpecification request;
    private Response response;

    @Given("^I have access to perform GET request with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iHaveAccessToPerformGETRequestWithUsernameAndPassword(String username, String password)  {
        request = given().
                baseUri("http://bhdtest.endava.com:8080/petclinicSecured/api").
                auth().preemptive().basic(username,password).
                contentType(ContentType.JSON);
    }

    @When("^I perform a GET request to \"([^\"]*)\"$")
    public void iPerformAGETRequestTo(String path)   {
        response =request.when().get(path);
    }

    @Then("^I will have the status code \"([^\"]*)\"$")
    public void iWillHaveTheStatusCode(int statusCode)  {
        response.then().statusCode(statusCode);
        System.out.println(response.asString());
    }


}
