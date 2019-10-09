package OauthDemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PetfinderAPITestingOauth2 {
	
	String accesstoken="";
	
  public void getFindAnimalsAPITest() {
	  
	  Response rs=RestAssured.given()
			  .formParam("client_id", "dCNxZghY1mhBmvyXwWu0cLGJvKLjkiXzp1wyGKXVk7gWqnhsiL")
			  .formParam("client_secret", "dT9113L9sGunBMORR90JEzlel3tRXA2GFY5MLR87")
			  .formParam("grant_type", "client_credentials")
			  .post("https://api.petfinder.com/v2/oauth2/token");
	  accesstoken=rs.jsonPath().get("access_token");
	  System.out.println(rs.statusCode());
	 // System.out.println(accesstoken);
	  Assert.assertEquals(200,rs.getStatusCode());
	  	//System.out.println(rs.body().jsonPath().prettify());
	 
  }
  
  @Test 
  public void getFindAnimalsTest() {
	  getFindAnimalsAPITest();
	  Response rs=RestAssured.given()
			  .auth()
			  .oauth2(accesstoken)
			  .get("https://api.petfinder.com/v2/animals");
	  System.out.println(rs.getStatusCode());
	  //System.out.println(rs.body().jsonPath().prettify());
	  Assert.assertEquals(200,rs.getStatusCode());
  }
  
  @Test
  public void getAnimalBasedOnID() {
	  
	  
	  getFindAnimalsAPITest();
	  int id=46162228;
	  Response rs=RestAssured.given()
			  .auth()
			  .oauth2(accesstoken)
			  .get("https://api.petfinder.com/v2/animals/"+id+"");
	  System.out.println(rs.getStatusCode());
	  System.out.println(rs.body().jsonPath().prettify());
	  Assert.assertEquals(200,rs.getStatusCode());
	  
  }
}
