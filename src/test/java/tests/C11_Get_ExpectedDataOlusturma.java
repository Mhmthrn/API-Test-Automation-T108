package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C11_Get_ExpectedDataOlusturma {
    /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine
    bir GET request yolladigimizda donen response body’sinin
    asagida verilen ile ayni oldugunu test ediniz
   Response body :
    {
    "userId":3,
    "id":22,
    "title":"dolor sint quo a velit explicabo quia nam",
    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
    um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }
     */

    @Test
    public void get01(){

        //1- endpoint ve ihtiyac varsa request body olustur

        String url="https://jsonplaceholder.typicode.com/posts/22";

        //2- Expected data olustur

        JSONObject expectedBody=new JSONObject();
        expectedBody.put("userId",3);
        expectedBody.put("id",22);
        expectedBody.put("title","dolor sint quo a velit explicabo quia nam");
        expectedBody.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        //3- request gonder ve donen responsu kaydet

        Response response=given().when().get(url);

        //4- Assertion
        // not : donen responsun body si ile islem yapmak istuyorsak
        // bunu jsonpath objesine donusturmmemiz gerekiyor

        JsonPath responsePath=response.jsonPath();

        assertEquals(expectedBody.get("userId"),responsePath.get("userId"));
        assertEquals(expectedBody.get("id"),responsePath.get("id"));
        assertEquals(expectedBody.get("title"),responsePath.get("title"));
        assertEquals(expectedBody.get("body"),responsePath.get("body"));

    }
}
