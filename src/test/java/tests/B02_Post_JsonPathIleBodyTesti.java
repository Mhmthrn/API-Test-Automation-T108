package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class B02_Post_JsonPathIleBodyTesti {
    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
            request gonderdigimizde
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


            POST REQUEST, RESPONSE BODY BILGILERINI
            ASSERT YAPARKEN JSONPATH KULLANMA
            donen Response’un,
            status code’unun 200,
            ve content type’inin application-json,
            ve response body’sindeki
            "firstname“in,"Ahmet",
            ve "lastname“in, "Bulut",
            ve "totalprice“in,500,
            ve "depositpaid“in,false,
            ve "checkin" tarihinin 2021-06-01
            ve "checkout" tarihinin 2021-06-10
            ve "additionalneeds“in,"wi-fi"
            oldugunu test edin

                 */

    @Test
    public void post01(){

        //1-Endpoint olusturulur ve varsa request body olusturul

        String url="https://restful-booker.herokuapp.com/booking";


        JSONObject bookingdates=new JSONObject();
        bookingdates.put("checkin" , "2021-06-01");
        bookingdates.put("checkout" , "2021-06-10");

        JSONObject requestBody=new JSONObject();
        requestBody.put("firstname" , "Ahmet");
        requestBody.put("lastname" , "Bulut");
        requestBody.put("totalprice" , 500);
        requestBody.put("depositpaid" , false);
        requestBody.put("additionalneeds" , "wi-fi");
        requestBody.put("bookingdates" , bookingdates);

        //2- expected data olusturuyoruz eger ihtiyac varsa

        //3- Request gonder ve donen response kaydet

        Response response=given().contentType(ContentType.JSON)
                                .when().body(requestBody.toString()).post(url);


        //4- Assertion

        JsonPath responseJsonPath=response.jsonPath();


       Assert.assertEquals(200,response.getStatusCode());
       Assert.assertEquals("application/json; charset=utf-8",response.contentType());

       Assert.assertEquals("Ahmet",responseJsonPath.get("booking.firstname"));
       Assert.assertEquals("Bulut",responseJsonPath.get("booking.lastname"));
       Assert.assertEquals(500 ,responseJsonPath.getInt("booking.totalprice"));
       Assert.assertEquals(false,responseJsonPath.getBoolean("booking.depositpaid"));
       Assert.assertEquals("wi-fi" ,responseJsonPath.getString("booking.additionalneeds"));
       Assert.assertEquals("2021-06-01" ,responseJsonPath.getString("booking.bookingdates.checkin"));
       Assert.assertEquals("2021-06-10" ,responseJsonPath.getString("booking.bookingdates.checkout"));


    }
}
