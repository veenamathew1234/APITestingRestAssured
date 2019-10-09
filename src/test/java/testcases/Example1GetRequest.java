package testcases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Example1GetRequest {
  @Test
  public void verifyGetRequest() {
	  RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	  
	  RequestSpecification httprequest=RestAssured.given();
	  //Response response=httprequest.request(Method.GET, "/Hyderabad");
	  Response response=httprequest.get("/Hyderabad");
	  
	  String responseBody=response.getBody().asString();
	  System.out.println(responseBody);
//	  
//	  System.out.println("Verifying the status code");
//	  int statuscode=response.getStatusCode();
//	  Assert.assertEquals("Not expected status code", 200, statuscode);
//
//	  String statusline=response.getStatusLine();
//	  Assert.assertEquals("Not the expected status line", "HTTP/1.1 200 OK", statusline);
//	  
	  System.out.println("Verifying the headers");
	 // System.out.println("The headers="+response.getHeaders());
	  System.out.println("----------------------------------------------------------------------------");
	  String contentType=response.getHeader("Content-Type");
	  String cacheControl=response.getHeader("Cache-Control");
	  System.out.println("Content type="+contentType);
	  System.out.println("Cache-Control="+cacheControl);
	  Assert.assertEquals("Content Type not matching", "application/json", contentType);
	  Assert.assertEquals("Cache Control not matching", "max-age=0", cacheControl);
	  
	  System.out.println("Verifying all the nodes using JSON Path");
	  System.out.println("--------------------------------------------------------");
	  JsonPath jsoneval=response.jsonPath();
	  System.out.println("City from json="+jsoneval.get("City"));
	  System.out.println("Weather from json="+jsoneval.get("WeatherDescription"));
	  
	  
	  
  }
}
