package tests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class C14_Put_SoftAssertIleExpectedDataTesti {


    /*
    C14_Put_SoftAssertIleExpectedDataTesti
                    http://dummy.restapiexample.com/api/v1/update/21 url’ine asagidaki body’ye sahip bir PUT
                    request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
                    Request Body
                    {
                    "status": "success",
                    "data": {
                    "name": “Ahmet",
                    "salary": "1230",
                    "age": "44",
                    "id": 40
                    }
                    }
                    Response Body
                    { "status": "success",
                    "data": {
                    "status": "success",
                    "data": {
                    "name": “Ahmet",
                    "salary": "1230",
                    "age": "44",
                    "id": 40 }
                    },
                    "message": "Successfully! Record has been updated."
     */

    @Test
    public void put01(){
        //1- endpoit olustur requwust body olustur

        String url="https://dummy.restapiexample.com/api/v1/update/1";

        JSONObject data=new JSONObject();
        data.put("name", "Ahmet");
        data.put("salary", "1230");
        data.put("age", "44");
        data.put("id", 40);

        JSONObject reqBody=new JSONObject();

        reqBody.put( "status", "success");
        reqBody.put( "data", data);


        //2- expected data varsa olustur

        JSONObject expectedData=new JSONObject();

        expectedData.put("status", "success");
        expectedData.put("data", reqBody);
        expectedData.put("message", "Successfully! Record has been updated.");

      //3- Request gonder ve donen responsu kaydet

        Response response=given()
                .contentType(ContentType.JSON).when().body(reqBody.toString()).put(url);
        response.prettyPrint();

      //4- Assertion

       JsonPath responseJsonPath=response.jsonPath();

        SoftAssert softAssert=new SoftAssert();

       assertEquals(responseJsonPath.getString("status"),expectedData.get("status"));
       assertEquals(responseJsonPath.getString("message"),expectedData.getString("message"));
       assertEquals(responseJsonPath.get("data.status"),expectedData.getJSONObject("data").get("status"));
       assertEquals(responseJsonPath.get("data.data.name"),expectedData.getJSONObject("data").getJSONObject("data").get("name"));
       assertEquals(responseJsonPath.get("data.data.id"),expectedData.getJSONObject("data").getJSONObject("data").get("id"));
       assertEquals(responseJsonPath.get("data.data.salary"),expectedData.getJSONObject("data").getJSONObject("data").get("salary"));
       assertEquals(responseJsonPath.get("data.data.age"),expectedData.getJSONObject("data").getJSONObject("data").get("age"));

        softAssert.assertAll();





    }
}
