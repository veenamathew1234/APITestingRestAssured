package cookiesbasedauth;

import javax.swing.text.AbstractDocument.Content;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateIssueInJira {
	
	String jsessionID;
  @Test
  public void createJSessionID() {
	  
	  JSONObject json=new JSONObject();
	  json.put("username", "veenamathew1234");
	  json.put("password", "Welcome123ra");
	  
//	 Response rs= RestAssured.given()
//	  .header("Content-Type", "application/json")
//	  .body(json.toJSONString())
//	  .post("http://localhost:8080/rest/auth/1/session");
	 
	  RequestSpecification request=RestAssured.given();
	  request.header("Content-Type","application/json");
	  request.body(json.toJSONString());
	  Response rs=request.post("http://localhost:8080/rest/auth/1/session");
	  
	 System.out.println(rs.getBody().jsonPath().prettify());
	 jsessionID=rs.getCookies().get("JSESSIONID");
	 System.out.println("cookies="+rs.getCookies().get("JSESSIONID"));
  }
  
  @Test
  
  public void createJiraTask()
  {
	  Response response=RestAssured.given()
	  .contentType(ContentType.JSON)
	  .cookie(jsessionID)
	  .body("{\\n    \\\"fields\\\": {\\n       \\\"project\\\":\\n       {\\n          \\\"key\\\": \\\"TEST\\\"\\n       },\\n       \\\"summary\\\": \\\"REST ye merry gentlemen.\\\",\\n       \\\"description\\\": \\\"Creating of an issue using project keys and issue type names using the REST API\\\",\\n       \\\"issuetype\\\": {\\n          \\\"name\\\": \\\"Bug\\\"\\n       }\\n   }\\n}")
	  .post("http://localhost:8080/rest/api/2/project");
	  System.out.println(response.getBody().jsonPath().prettify());
	  
  }
}
