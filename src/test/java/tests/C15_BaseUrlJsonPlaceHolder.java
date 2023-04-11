package tests;

import baseUrl.JsonPlaceholderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C15_BaseUrlJsonPlaceHolder  extends JsonPlaceholderBaseUrl {
    /*
    Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin
     */
    @Test
    public void get01(){
        /*
        1- https://jsonplaceholder.typicode.com/posts endpointine bir GET request
        gonderdigimizde donen response’un status code’unun 200 oldugunu ve
        Response’ta 100 kayit oldugunu test edin
         */

        //1- end point ve vasra request body olustur
        specJsonPlaceholder.pathParam("pp1","posts");

        //2- varsa expected data olustur

        //3- request gonder ve donen responsu kaydet
        Response response=given().spec(specJsonPlaceholder).when().get("/{pp1}");
        response.prettyPrint();

        //4- Assertion
        response.then().assertThat()
                .statusCode(200)
                .body("id", Matchers.hasSize(100));
    }


    @Test
    public void get02(){
        /*
        2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET request
        gonderdigimizde donen response’un status code’unun 200 oldugunu ve “title”
        degerinin “optio dolor molestias sit” oldugunu test edin
         */
        //1- end point olustur ve varsa reqquest body olustur
        specJsonPlaceholder.pathParams("pp1","posts","pp2","44");

        //2- expected data varsa olustur

        //3- request gonder ve donen responsu kaydet
        Response response=given().spec(specJsonPlaceholder).when().get("/{pp1}/{pp2}");

        //4- Assertion

        response.then().assertThat()
                .statusCode(200)
                .body("title",Matchers.equalTo("optio dolor molestias sit"));
    }


    @Test
    public void delete01(){
        /*
          3- https://jsonplaceholder.typicode.com/posts/50 endpointine bir DELETE request
        gonderdigimizde donen response’un status code’unun 200 oldugunu ve response
        body’sinin null oldugunu test edin
         */

        //1- end point ve varsa request body olustur
        specJsonPlaceholder.pathParams("pp1","posts","pp2","50");

        //2- Expected data varsa olustuturun

        //3-request gonder responsu kadet
        Response response=given().spec(specJsonPlaceholder).when().delete("/{pp1}/{pp2}");

        //4- Assertion

        response.then().assertThat()
                .statusCode(200)
                .body("id",Matchers.nullValue());

    }

}
