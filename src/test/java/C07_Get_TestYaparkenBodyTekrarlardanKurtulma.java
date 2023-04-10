import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class C07_Get_TestYaparkenBodyTekrarlardanKurtulma {
    @Test
    public void test01(){

        /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde
            donen Response’un,
            status code’unun 200,
            ve content type’inin application-json,
            ve response body’sindeki
            "firstname“in, "Susan",
            ve "lastname“in, "Jackson",
            ve "totalprice“in, 1000 den kucuk oldugunu,
            ve "depositpaid“in, false,
            ve "additionalneeds“in, bos burakilmadigi
            oldugunu test edin

         */

        //1- end point ve request body hazirlamak
        String url="https://restful-booker.herokuapp.com/booking/10";

        //2- expected data olustur

        //3- request gonderip donen responsu kaydet
        Response response=given().when().get(url);

        //4- Assertion
        response.prettyPrint();

       /* response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", Matchers.equalTo("Susan"))
                .body("lastname",Matchers.equalTo("Jackson"))
                .body("totalprice",Matchers.lessThan(1000))
                .body("depositpaid",Matchers.equalTo(true))
                .body("additionalneeds",Matchers.notNullValue());

*/
       response.then()
               .assertThat()
               .statusCode(200)
               .contentType(ContentType.JSON)
               .body("firstname",equalTo("Jim"),
                          "lastname", equalTo("Jones"),
                       "totalprice",lessThan(1000),
                       "depositpaid", equalTo(true),
                       "additionalneeds",notNullValue());


    }
}
