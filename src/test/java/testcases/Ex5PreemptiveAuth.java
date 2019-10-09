package testcases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import utils.SetUp;

public class Ex5PreemptiveAuth extends SetUp {
  @Test
  public void test1() {
	  
	  int statuscode=RestAssured.given()
	  .get()
	  .getStatusCode();
	  
	  System.out.println("response code from server="+statuscode);
  }
}
