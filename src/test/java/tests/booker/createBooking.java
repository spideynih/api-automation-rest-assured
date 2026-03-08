package tests.booker;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class createBooking extends BaseTest {

    BaseTest baseUrl = new BaseTest();
    Response response;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = baseUrl.getBookerBaseUrl();
    }

    //method untuk membuat payload create booking
    public JSONObject bookingPayload(){
        //generate json bookingsdate
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin","2026-04-10");
        bookingdates.put("checkout","2026-04-10");

        JSONObject data = new JSONObject();
        data.put("firstname","Nimas");
        data.put("lastname","QA");
        data.put("totalprice",2500);
        data.put("depositpaid",false);
        data.put("bookingdates",bookingdates);
        data.put("additionalneeds", "Breakfast");

        return data;
    }

    @Test
    public void createBooking(){
        JSONObject payload = bookingPayload();

        response = given()
                .header("Content-Type", "application/json")
                .body(payload.toJSONString())
                .when()
                .post("booking")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("=== Get /booking ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asPrettyString());

    }


}
