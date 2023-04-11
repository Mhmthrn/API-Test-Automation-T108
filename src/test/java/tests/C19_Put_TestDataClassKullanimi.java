package tests;

import baseUrl.JsonPlaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class C19_Put_TestDataClassKullanimi  extends JsonPlaceholderBaseUrl {
    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir
        PUT request yolladigimizda donen response’in
        status kodunun 200, content type’inin “application/json; charset=utf-8”,
        Connection header degerinin “keep-alive”
        ve response body’sinin asagida verilen ile ayni oldugunu test ediniz


     */

    @Test
    public void  put01(){
        //1
        specJsonPlaceholder.pathParams("pp1","posts","pp2","70");

        JSONObject reqBody=new JSONObject();

        reqBody.put("title", "Ahmet");
        reqBody.put("body", "Merhaba");
        reqBody.put("userId", 10);
        reqBody.put("id", 70);

       //2-

        //3-
        Response response=given().contentType(ContentType.JSON).spec(specJsonPlaceholder).when().body(reqBody.toString()).put("/{pp1}/{pp2}");

        //4-
        response.then().assertThat().statusCode(200).header("Connection", Matchers.equalTo( "keep-alive")).body("body",Matchers.equalTo("Merhaba"));


    }
}
