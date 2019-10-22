package OauthDemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class OAuth1Demo {
	String id="1186599989906010112";
  @Test(priority=1)
  public void postTweet() {
	  
	  Response rs=RestAssured.given()
			  .auth()
			  .oauth("mlmi9omirl9XwxVhr84ZMhJHJ", "gORYO3E5paFce55rs05i1IbicdUIqNSXT3tW8wQPRVuHoIck9z", 
					  "4519554942-O71QIaaHuQ8MvlqGnfYoLYvrPUG4842wC62k7U3", "2ZF0d1KCSqrVUN6IGzvcYYWc7TUsL8csRMDDlwqbZxBrd")
			  .post("https://api.twitter.com/1.1/statuses/update.json?status=Tweet via API trial 5");
	  System.out.println(rs.getStatusCode());
	  System.out.println(rs.getBody().jsonPath().prettify());
	  id=rs.getBody().jsonPath().get("id_str");
	  System.out.println("My trial 5 tweet id is"+id);
  }
  
  @Test(priority=2)
  public void deleteTweet() throws InterruptedException {
	  
	  System.out.println("Tweet to be deleted is="+id);
	  Response rs=RestAssured.given()
			  .auth()
			  .oauth("mlmi9omirl9XwxVhr84ZMhJHJ", "gORYO3E5paFce55rs05i1IbicdUIqNSXT3tW8wQPRVuHoIck9z", 
					  "4519554942-O71QIaaHuQ8MvlqGnfYoLYvrPUG4842wC62k7U3", "2ZF0d1KCSqrVUN6IGzvcYYWc7TUsL8csRMDDlwqbZxBrd")
			  .post("https://api.twitter.com/1.1/statuses/destroy/"+id+".json");
	  System.out.println(rs.getStatusCode());
	  Assert.assertEquals(200, rs.getStatusCode());
  }
}
