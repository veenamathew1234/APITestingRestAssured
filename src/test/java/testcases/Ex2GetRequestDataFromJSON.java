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

public class Ex2GetRequestDataFromJSON {
  @Test
  public void verifyGetRequest() {
	  RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	  
	  RequestSpecification httprequest=RestAssured.given();
	  //Response response=httprequest.request(Method.GET, "/Hyderabad");
	  Response response=httprequest.get("/Hyderabad");
	  
	  String responseBody=response.getBody().asString();
	  System.out.println(responseBody);
	  
	  System.out.println("Verifying all the nodes using JSON Path");
	  System.out.println("--------------------------------------------------------");
	  JsonPath jsoneval=response.jsonPath();
	  System.out.println("City from json="+jsoneval.get("City"));
	  Assert.assertEquals("City from json doesnt match","Hyderabad" , jsoneval.get("City"));
	  System.out.println("Weather from json="+jsoneval.get("WeatherDescription"));
	  
	  
	  
  }
}
