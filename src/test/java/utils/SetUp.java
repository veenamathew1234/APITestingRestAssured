package utils;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class SetUp {
	
	@BeforeClass
	public void credsetup()
	{
		RestAssured.authentication=RestAssured.preemptive().basic("ToolsQA", "TestPassword");
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
	}

}
