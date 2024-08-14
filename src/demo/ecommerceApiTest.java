package demo;

import static io.restassured.RestAssured.*;
import java.io.File;
//import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
//import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.loginrequest;
import pojo.loginresponse;

public class ecommerceApiTest {

	public static void main(String[] args) {
		
		//login
		
//		RestAssured.baseURI = "https://rahulshettyacademy.com";
//		
//		String response = given().log().all().header("Content-Type","application/json")
//				.body("{\r\n"
//				+ "    \"userEmail\": \"kapalavai@gmail.com\",\r\n"
//				+ "    \"userPassword\": \"K@12345678k\"\r\n"
//				+ "}").when().post("/api/ecom/auth/login").then().log().all().assertThat().statusCode(200).extract().asString();
//		
//		//JsonPath jp = new JsonPath(response);
//		
//		System.out.println(response);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		loginrequest logreq = new loginrequest();
		logreq.setUserEmail("kapalavai@gmail.com");
		logreq.setUserPassword("K@12345678k");
		RequestSpecification reqlog = given().log().all().spec(req).body(logreq);
		loginresponse logresp = reqlog.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(loginresponse.class);
		
		System.out.println(logresp.getToken());
		String token = logresp.getToken();
		System.out.println(logresp.getUserId());
		String userId = logresp.getUserId();
		System.out.println(logresp.getMessage());
		
		//Add product
		RequestSpecification addProd = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();
		RequestSpecification requestspec = given().log().all().spec(addProd).param("productName", "iphone 14").param("productAddedBy", userId)
		.param("productCategory", "fashion").param("productSubCategory", "shirts")
		.param("productPrice", "11500").param("productDescription","Apple Iphone 14 pro")
		 .param("productFor", "men").multiPart(new File("C:x//Users//Venky//Desktop//image.png"));
		String addProductResponse = requestspec.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
		
		JsonPath obj = new JsonPath(addProductResponse);
		String productId = obj.get("productId");
		System.out.println(productId);
	}

}
