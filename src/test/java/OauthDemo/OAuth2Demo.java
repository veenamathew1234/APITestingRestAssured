package OauthDemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class OAuth2Demo  {
  @Test
  public void accessValidAPI() {
	  
	  Response res=RestAssured.given()
			  .formParam("client_id", "LearnAutomation")
			  .formParam("client_secret", "9e62312b97066210c18089b78f84e1ce")
			  .formParam("grant_type", "client_credentials")
			  .post("http://coop.apps.symfonycasts.com/token");
			  System.out.println(res.getBody().jsonPath().prettify());
			  System.out.println(res.getBody().jsonPath().get("access_token"));
			  String token=res.getBody().jsonPath().get("access_token");
			  
	  Response rs=RestAssured
			  .given()
			  .auth()
			  .oauth2(token)
			  .post("http://coop.apps.symfonycasts.com/api/443/chickens-feed");
	  System.out.println(rs.getBody().asString());
	  int code=rs.getStatusCode();
	  Assert.assertEquals("Not expected code", 200, code);
	  
	  
  }
  
//  @Test
//  public void acessgetAPI() {
//	  
//	  Response rs=
//			  RestAssured.given()
//			  .formParam("client_id", "LearnAutomation")
//			  .formParam("response_type", "token")
//		  .formParam("redirect_uri", "http://coop.apps.symfonycasts.com/api/me")
//		//	  .formParam("scope", "chickens-feed")
//			  .get("http://coop.apps.symfonycasts.com/authorize");
//	  System.out.println(rs.getBody().jsonPath().prettify());
//  }
  
}
