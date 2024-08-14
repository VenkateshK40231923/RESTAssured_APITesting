package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.mainSystemTwo;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;



public class specBuilderTest {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		mainSystemTwo obj1 = new mainSystemTwo();
		
		obj1.setAccuracy(50);
		obj1.setAddress("10 Rue Troy");
		obj1.setLanguage("french");
		obj1.setName("Venky");
		obj1.setPhone_number("5146383528");
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		obj1.setTypes(mylist);
		obj1.setWebsite("www.google.com");
		
		Location l = new Location();
		l.setLng(-54.55465);
		l.setLat(45.21532);
		
		obj1.setLocation(l);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification reqspec = given().spec(req).body(obj1);
		
		Response resp = reqspec.when().post("/maps/api/place/add/json")
				.then().spec(res).extract().response();
		String responseString = resp.asString();
		
		System.out.println(responseString);
	}

}
