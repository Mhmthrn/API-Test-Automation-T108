package tests;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C16_BaseUrlHerokuapp extends HerokuappBaseUrl {
    /*
    C16_BaseUrlHerokuapp
            Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin
            1- https://restful-booker.herokuapp.com/booking endpointine bir GET request
            gonderdigimizde donen response’un status code’unun 200 oldugunu ve
            Response’ta 12 booking oldugunu test edin

            2- https://restful-booker.herokuapp.com/booking
            endpointine asagidaki body’ye sahip bir POST
            request gonderdigimizde donen response’un
            status code’unun 200 oldugunu ve “firstname”
            degerinin “Ahmet” oldugunu test edin
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
     */

    @Test
    public void get01(){

        //1- end point olustur ve varsa rewquest gonder

        specHerokuapp.pathParam("pp1","booking");

        //2- expected data

        //3- request gonder responsu kaydet

        Response response=given().spec(specHerokuapp).when().get("/{pp1}");
        response.prettyPrint();

        //4- Assertion

        response.then().assertThat()
                        .statusCode(200)
                        .body("bookingid", Matchers.hasSize(12));


    }

    @Test
    public void post01(){

        //1-
        specHerokuapp.pathParam("pp1","booking");

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
        //2-

        //3-
        Response response=given().contentType(ContentType.JSON).spec(specHerokuapp).when().body(requestBody.toString()).post("/{pp1}");

        JsonPath responseJPath=response.jsonPath();




        //4-

       assertEquals( response.statusCode(),200);
       assertEquals(responseJPath.get("booking.firstname"),"Ahmet");


    }




}
