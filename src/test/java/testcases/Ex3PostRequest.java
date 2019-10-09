package testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Ex3PostRequest {
  @Test
  public void postRequest() {
	  
	  RestAssured.baseURI="http://restapi.demoqa.com/customer";
	  RequestSpecification request=RestAssured.given();
	  JSONObject params=new JSONObject();
	  params.put("FirstName", "venM");
	  params.put("LastName", "Mthw");
	  params.put("UserName", "vena134");
	  params.put("Password", "passwrd");
	  params.put("Email", "veen@mailinator.com");
	  request.body(params.toJSONString());
	  Response response=request.post("/register");
	  int statuscode=response.getStatusCode();
	  Assert.assertEquals("Status code not matching", 201, statuscode);
	  //System.out.println(response.body().asString());
	  String successcode=response.jsonPath().get("SuccessCode");
	  System.out.println(successcode);
	  
	  
  }
}
