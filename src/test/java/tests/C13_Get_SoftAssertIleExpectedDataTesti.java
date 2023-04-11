package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.lang.module.ResolutionException;

import static io.restassured.RestAssured.given;

public class C13_Get_SoftAssertIleExpectedDataTesti {

    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
        Response Body
        {
        "status":"success",
        "data":{
                "id":3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
                },
        "message":"Successfully! Record has been fetched."
        }
     */
    @Test
    public void get01(){

        //1- ent point olustur
        String url="http://dummy.restapiexample.com/api/v1/employee/3";

        //2- expected data olustur
        JSONObject expecteData=new JSONObject();
        JSONObject data=new JSONObject();
        data.put("id",3);
        data.put("employee_name","Ashton Cox");
        data.put("employee_salary",86000);
        data.put("employee_age",66);
        data.put("profile_image","");

        expecteData.put("status","success");
        expecteData.put("data",data);
        expecteData.put("message","Successfully! Record has been fetched.");

        System.out.println(expecteData);

        //3- Request gonder donen responsu kaydet

        Response response=given().when().get(url);

        //4- Assertion

        JsonPath responseJsPath=response.jsonPath();

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(responseJsPath.get("status"),expecteData.get("status"));
        softAssert.assertEquals(responseJsPath.get("message"),expecteData.get("message"));
        softAssert.assertEquals(responseJsPath.get("data.id"),expecteData.getJSONObject("data").get("id"));
        softAssert.assertEquals(responseJsPath.get("data.employee_name"),expecteData.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(responseJsPath.get("data.employee_salary"),expecteData.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(responseJsPath.get("data.employee_age"),expecteData.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(responseJsPath.get("data.profile_image"),expecteData.getJSONObject("data").get("profile_image"));


        softAssert.assertAll();

    }


}
