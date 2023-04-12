package tests;

import baseUrl.DummyBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import testData.TestDataDummy;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C20_Get_TestDataKullanimi extends DummyBaseURL {


   /* http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un status code’unun 200, content Type’inin
    application/json ve body’sinin asagidaki gibi oldugunu test edin.
    Response Body
     {
        "status": "success",
                "data": {
                "id": 3,
                "employee_name": "Ashton Cox",
                "employee_salary": 86000,
                "employee_age": 66,
                "profile_image": ""
     },
        "message": "Successfully! Record has been fetched."
    }*/

    @Test
    public void get01(){
        //1- endpoint olustur ve varsa request body olustu
        specDummy.pathParams("pp1","employee","pp2","3");

        //2- expected data varsa
        TestDataDummy testDataDummy=new TestDataDummy();

        JSONObject expectedData= testDataDummy.expectedBodyOlusturJson();

        System.out.println(expectedData);


        //3- request gonder donen responsu kaydet

        Response response=given().spec(specDummy).when().get("/{pp1}/{pp2}");

        //4-
        JsonPath responsePath=response.jsonPath();

        SoftAssert softAssert=new SoftAssert();

        assertEquals(response.getStatusCode(),testDataDummy.basariliStatusCode);
        assertEquals(response.contentType(),testDataDummy.contentType);

        assertEquals(responsePath.get("status"),expectedData.get("status"));
        assertEquals(responsePath.get("message"),expectedData.get("message"));
        assertEquals(responsePath.get("data.id"),expectedData.getJSONObject("data").get("id"));
        assertEquals(responsePath.get("data.employee_name"),expectedData.getJSONObject("data").get("employee_name"));
        assertEquals(responsePath.get("data.employee_salary"),expectedData.getJSONObject("data").get("employee_salary"));
        assertEquals(responsePath.get("data.employee_age"),expectedData.getJSONObject("data").get("employee_age"));
        assertEquals(responsePath.get("data.profile_image"),expectedData.getJSONObject("data").get("profile_image"));

        softAssert.assertAll();

    }





}
