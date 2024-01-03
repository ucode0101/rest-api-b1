package com.ucodeacademy.day_02_path_query_param_tokens;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;


public class GetRequest {
   // static String baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";

    @BeforeClass
    public static void setUp(){
        // baseURI is just a static String that comes from RestAssured class
        baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";


    }


    @Test
    public void getAllVendors(){
       given().get("/vendors").then().log().body();

    }

    @Test
    public void getVendorById(){
        //given().pathParams("id","3").when().get("/vendors/{id}").then().log().body();
        given().when().get("/vendors/3").then().log().body();

    }

    @Test
    public void getProductQueryParam(){
        given().queryParam("search", "Pineapple")
                .queryParam("limit",1)
                .when()
                .get("/products")
                .then().log().body();
    }

}
