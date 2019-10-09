package testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Ex4PostDeletePut {
	
	
  @Test(priority=1)
  public void test1() 
  
  {
	
	  RequestSpecification request= RestAssured.given();
	  request.header("Content-Type","application/json");
	  JSONObject jb=new JSONObject();
	  jb.put("id", "veena");
	  jb.put("title", "ksduy");
	  jb.put("author", "vee");
	  request.body(jb.toJSONString());
	  Response response=request.post("http://localhost:3000/posts");
	  int statuscode=response.getStatusCode();
	  Assert.assertEquals("Not expected", 201, statuscode);
	  
  }
  
  
  @Test(priority=2)
  public void test2()
  {
	  RequestSpecification request=RestAssured.given();
	  JSONObject jb=new JSONObject();
	  jb.put("id", "veena");
	  jb.put("title", "ksduy");
	  jb.put("author", "veenamathew");
	  request.body(jb.toJSONString());
	  Response response=request.put("http://localhost:3000/posts/veena");
	  int statuscode=response.getStatusCode();
	  Assert.assertEquals("Not expected", 200, statuscode);
  }
  
  @Test(priority=3)
  public void test3()
  {
	  RequestSpecification request=RestAssured.given();
	  Response response=request.delete("http://localhost:3000/posts/veena");
	  int statuscode=response.getStatusCode();
	  Assert.assertEquals("Not expected", 200, statuscode);
  }
  
}
  

