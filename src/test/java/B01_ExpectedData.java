import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class B01_ExpectedData {

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
    public void post01(){

        //1- Endpoint ve varsa request body olustur

        String  url="https://restful-booker.herokuapp.com/booking";

        JSONObject bookingDates=new JSONObject();
        bookingDates.put("checkin" , "2021-06-01");
        bookingDates.put("checkout" , "2021-06-01");


        JSONObject requestBody=new JSONObject();
        requestBody.put("firstname" , "Ahmet");
        requestBody.put("lastname" ,  "Bulut");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid" , false);
        requestBody.put("bookingdates" , bookingDates);
        requestBody.put("additionalneeds" ,  "wi-fi");

        //2- Expected data varsa olustur

        JSONObject expactedBody=new JSONObject();

        expactedBody.put("bookingid",24);
        expactedBody.put("booking",requestBody);

        //3- Request gonder donen response kaydet

        Response response=given().contentType(ContentType.JSON)
                            .when().body(requestBody.toString())
                            .post(url);

        //4- Assertion
        JsonPath reponseJsonPath=response.jsonPath();

        assertEquals(expactedBody.getJSONObject("booking").get("firstname"),reponseJsonPath.get("booking.firstname"));
        assertEquals(expactedBody.getJSONObject("booking").get("additionalneeds"),reponseJsonPath.get("booking.additionalneeds"));
        assertEquals(expactedBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                            reponseJsonPath.get("booking.bookingdates.checkin"));
        assertEquals(expactedBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                reponseJsonPath.get("booking.bookingdates.checkout"));

        assertEquals(expactedBody.getJSONObject("booking").get("totalprice"),reponseJsonPath.get("booking.totalprice"));
        assertEquals(expactedBody.getJSONObject("booking").get("depositpaid"),reponseJsonPath.get("booking.depositpaid"));
        assertEquals(expactedBody.getJSONObject("booking").get("lastname"),reponseJsonPath.get("booking.lastname"));

    }
}
