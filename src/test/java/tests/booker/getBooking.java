package tests.booker;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getBooking extends BaseTest {

    BaseTest baseUrl = new BaseTest();
    Response response;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = baseUrl.getBookerBaseUrl();
    }

    //get booking all
    @Test
    public void getBooking() {
        Response response = given()
                .when()
                .get("booking")
                .then()
                .extract().response();

        System.out.println("=== Get /booking ===");
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.asPrettyString());
    }


    //get booking by id
    @Test
    public void getBookingIds(){
        Response response = given()
                .when()
                .get("booking/1")
                .then()
                .statusCode(200) //Assertion Status Code
                .contentType(ContentType.JSON)
                .extract().response(); //buat dapetin response

        //Log Response
        System.out.println("=== Get /booking ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asPrettyString());

        // Verify Assertion Get booking ID 200
        // Assert firstname, Assert lastname, assert total Price
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("firstname"), "Sally");
        Assert.assertEquals(jsonPath.getString("lastname"), "Smith");
        Assert.assertEquals(jsonPath.getInt("totalprice"), 986);
    }


}
