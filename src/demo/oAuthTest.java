package demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.mainSystem;

import static io.restassured.RestAssured.*;

public class oAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		String response = given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type","client_credentials")
		.formParams("scope", "trust").when().post("oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println(response);
		
		JsonPath js1 = new JsonPath(response);
		String access_token = js1.getString("access_token");
		
		
		mainSystem obj = given().queryParam("access_token", access_token ).when().get("oauthapi/getCourseDetails").as(mainSystem.class);
		//System.out.println(obj.getLinkedIn());
		
		//System.out.println(obj.getCourses().getMobile().get(0).getCourseTitle());
		
		for(int i=0; i<obj.getCourses().getApi().size();i++) {
			if(obj.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(obj.getCourses().getApi().get(i).getPrice());
			}
		}
		
		for(int i=0; i<obj.getCourses().getWebAutomation().size();i++) {
			System.out.println(obj.getCourses().getWebAutomation().get(i).getCourseTitle());
		}
	} 

}
