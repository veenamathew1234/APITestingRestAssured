package OauthDemo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GenerateOauthToken {
	@Test

  public void generateToken() {
	  Response res=RestAssured.given()
	  .formParam("client_id", "LearnAutomation")
	  .formParam("client_secret", "9e62312b97066210c18089b78f84e1ce")
	  .formParam("grant_type", "client_credentials")
	  .post("http://coop.apps.symfonycasts.com/token");
	  System.out.println(res.getBody().jsonPath().prettify());
	  System.out.println(res.getBody().jsonPath().get("access_token"));
	  
	  String token=res.getBody().jsonPath().get("access_token");
	  
	  
	  
  }
}
