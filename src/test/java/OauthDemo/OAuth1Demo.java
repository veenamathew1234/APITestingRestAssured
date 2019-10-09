package OauthDemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth1Demo {
  @Test
  public void postTweet() {
	  
	  Response rs=RestAssured.given()
			  .auth()
			  .oauth("", "", "", "")
			  .post("https://api.twitter.com/1.1/statuses/update.json?status=tweet api");
	  System.out.println(rs.getBody().asString());
	  String id=rs.getBody().jsonPath().get("id_str");
  }
}
