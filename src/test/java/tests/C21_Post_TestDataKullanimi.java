package tests;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataHerokuapp;

import static io.restassured.RestAssured.given;

public class C21_Post_TestDataKullanimi extends HerokuappBaseUrl {
    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip
    bir POST request gonderdigimizde donen response’un status kodunu ve id haric
    body'sinin asagidaki gibi oldugunu test edin.
    Request body
          {
          "firstname" : "Ali",
          "lastname" : “Bak",
          "totalprice" : 500,
          "depositpaid" : false,
          "bookingdates" : {
                      "checkin" : "2021-06-01",
                      "checkout" : "2021-06-10"
                        },
          "additionalneeds" : "wi-fi"
           }
    Expected Body
    {
    "bookingid":24,
    "booking":{
            "firstname":"Ali",
            "lastname":"Bak",
            "totalprice":500,
            "depositpaid":false,
            "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                            },
            "additionalneeds":"wi-fi"
               }
    }
     */

    @Test
    public void post01(){

        //1-
        specHerokuapp.pathParam("pp1","booking");

        JSONObject requestBody= TestDataHerokuapp.requestBodyOlusturJson();

        //2-
        JSONObject expectedBody=TestDataHerokuapp.expectedBodyOlusturJson();

        //3-
        Response response=given()
                .contentType(ContentType.JSON).spec(specHerokuapp)
                .when().body(requestBody.toString())
                .post("/{pp1}");


        //4-

        JsonPath responseJsonPath=response.jsonPath();

        Assert.assertEquals(TestDataHerokuapp.statusCode, response.getStatusCode());
        Assert.assertEquals(expectedBody.getJSONObject("booking").get("firstname"),responseJsonPath.get("booking.firstname"));
        Assert.assertEquals(expectedBody.getJSONObject("booking").get("lastname"),responseJsonPath.get("booking.lastname"));
        Assert.assertEquals(expectedBody.getJSONObject("booking").get("totalprice"),responseJsonPath.get("booking.totalprice"));
        Assert.assertEquals(expectedBody.getJSONObject("booking").get("depositpaid"),responseJsonPath.get("booking.depositpaid"));


        Assert.assertEquals(expectedBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),responseJsonPath.get("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),responseJsonPath.get("booking.bookingdates.checkout"));

        Assert.assertEquals(expectedBody.getJSONObject("booking").get("additionalneeds"),responseJsonPath.get("booking.additionalneeds"));



    }
}
