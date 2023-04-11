package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {

    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
                        Request body
                   {
                        "firstname" : "Ahmet",
                        "lastname" : “Bulut",
                        "totalprice" : 500,
                        "depositpaid" : false,
                        "bookingdates" : {
                                 "checkin" : "2021-06-01",
                                 "checkout" : "2021-06-10"
                                          },
                        "additionalneeds" : "wi-fi"
                    }
                        Response Body
                   {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                             }
                    }
         */


    @Test
    public void post01() {

        //1- endpoint ve request body olustur
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();
        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin", "2021-06-01");
        bookingdates.put("checkout", "2021-06-10");

        requestBody.put("firstname", "Ahmet");
        requestBody.put("lastname", "Bulut");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("additionalneeds", "wi-fi");
        requestBody.put("bookingdates", bookingdates);


        //2- expected data olustur

        JSONObject expectedBody = new JSONObject();

        expectedBody.put("bookingid", 22);
        expectedBody.put("booking", requestBody);

        //3- response kaydet

        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString()).post(url);


        //4- Assertion

        JsonPath responseJsonPath = response.jsonPath();

        assertEquals(expectedBody.getJSONObject("booking").get("firstname"), responseJsonPath.get("booking.firstname"));
        assertEquals(expectedBody.getJSONObject("booking").get("lastname"), responseJsonPath.get("booking.lastname"));
        assertEquals(expectedBody.getJSONObject("booking").get("totalprice"), responseJsonPath.get("booking.totalprice"));
        assertEquals(expectedBody.getJSONObject("booking").get("firstname"), responseJsonPath.get("booking.firstname"));
        assertEquals(expectedBody.getJSONObject("booking").get("depositpaid"), responseJsonPath.get("booking.depositpaid"));
        assertEquals(expectedBody.getJSONObject("booking").getJSONObject("bookingdates")
                .getString("checkin"), responseJsonPath.get("booking.bookingdates.checkin"));
        assertEquals(expectedBody.getJSONObject("booking").getJSONObject("bookingdates")
                .getString("checkout"), responseJsonPath.get("booking.bookingdates.checkout"));
        assertEquals(expectedBody.getJSONObject("booking").get("additionalneeds"), responseJsonPath.get("booking.additionalneeds"));

    }
}


