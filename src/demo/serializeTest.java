package demo;

import io.restassured.RestAssured;
import pojo.Location;
import pojo.mainSystemTwo;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;



public class serializeTest {
	
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
		
		
		String response = given().log().all().queryParam("key", "qaclick123").body(obj1).when().post("/maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).extract().asString();
	}

}
