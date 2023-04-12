package tests;

import baseUrl.DummyBaseURL;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C23_Get_DeSerialization  extends DummyBaseURL {

    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
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
        }
     */

    @Test
    public void get01(){
        TestDataDummy testDataDummy=new TestDataDummy();

        //1-

        specDummy.pathParams("pp1","employee","pp2","3");

        //2-

        Map<String,Object> expectedBody= TestDataDummy.bizimExpectedMap();

        //3-

        Response response=given().spec(specDummy).when().get("/{pp1}/{pp2}");


        //4- Assertion

        HashMap responseMap= response.as(HashMap.class);

      assertEquals(testDataDummy.basariliStatusCode,response.getStatusCode());
      assertEquals(testDataDummy.contentType,response.contentType());


      assertEquals(expectedBody.get("status"),responseMap.get("status"));
      assertEquals(expectedBody.get("message"),responseMap.get("message"));

      assertEquals(((Map)(expectedBody.get("data"))).get("id"),((Map)(responseMap.get("data"))).get("id"));
      assertEquals(((Map)(expectedBody.get("data"))).get("employee_name"),((Map)(responseMap.get("data"))).get("employee_name"));
      assertEquals(((Map)(expectedBody.get("data"))).get("employee_salary"),((Map)(responseMap.get("data"))).get("employee_salary"));
      assertEquals(((Map)(expectedBody.get("data"))).get("employee_age"),((Map)(responseMap.get("data"))).get("employee_age"));
      assertEquals(((Map)(expectedBody.get("data"))).get("profile_image"),((Map)(responseMap.get("data"))).get("profile_image"));




    }
}
