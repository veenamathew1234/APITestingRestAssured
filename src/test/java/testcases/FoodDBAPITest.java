package testcases;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FoodDBAPITest {
  @Test
  public void getAllDictionaryInfo() {
	  
	  Response rs=RestAssured.given()
			  .header("Content-Type","application/json")
			  .formParam("app_id", "beec181e")
			  .formParam("app_key", "461b18c8015407e859c62f2a830f9f25")
			  .formParam("ingr", "apple")
			  .get("https://api.edamam.com/api/food-database/parser");
	  System.out.println(rs.getStatusCode());
	 // System.out.println(rs.jsonPath().prettify());
  }
  
  
  @Test
  public void nutritionDataTest() throws JsonParseException, IOException
  {
	  
	  File file = new File(System.getProperty("user.dir")+"/src/test/java/testcases/food.json");
	  	
	  ObjectWriter writer;
	  ObjectMapper mapper = new ObjectMapper();
	  Map<String,Object> DataObj;
		ObjectMapper mapperForWrite = new ObjectMapper();
		writer = mapperForWrite.writer(new DefaultPrettyPrinter());
		JsonFactory fUser = new JsonFactory();
		JsonParser jp1;
		jp1 = fUser.createParser(new File(System.getProperty("user.dir")+"/src/test/java/testcases/food.json"));
		jp1.nextToken();
		DataObj = mapperForWrite.readValue(jp1, Map.class);
	  
	  Response rs= RestAssured.given()
			  .formParam("app_id", "beec181e")
			  .formParam("app_key", "461b18c8015407e859c62f2a830f9f25")
			  .body(file)
			  .post("https://api.edamam.com/api/food-database/nutrients");
	  
	  System.out.println(rs.getStatusCode());
	  System.out.println(rs.jsonPath().prettify());
  }
}
