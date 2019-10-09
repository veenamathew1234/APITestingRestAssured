package OauthDemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BhagvatgitaAPIOauth2 {
	
	String access_token="";
  @Test
  public void getAcessToken() {
	  
	 Response rs= RestAssured.given()
	  .formParam("client_id", "oBhS8XR2PmEgAhmT3Pw0NNoCdy3GMY80i38ZRgio")
	  .formParam("client_secret", "K3BDjvz5pkwCdNwKcPpueS7DuSx5HGMs3XLX6dFyFXBQEtdFZP")
	  .formParam("grant_type", "client_credentials")
	  .formParam("scope", "verse")
	  .post("https://bhagavadgita.io/auth/oauth/token");
	  System.out.println(rs.getStatusCode());
	  System.out.println(rs.jsonPath().prettify());
	  access_token=rs.jsonPath().get("access_token");
	  System.out.println("Access token is"+ access_token);
	  
  }
  
  @Test
  
  public void getVersesFromChapter()
  {
	  Response rs=RestAssured.given()
	  .auth()
	  .oauth2(access_token)
	  .get("https://bhagavadgita.io/api/v1/chapters/1/verses");
	  System.out.println(rs.jsonPath().prettify());
	  
  }
}
