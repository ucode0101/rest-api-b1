package com.ucodeacademy.day_02_path_query_param_tokens;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class DeleteRequest {

    @BeforeClass
    public static void setUp(){
        baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";
    }

    @Test
    public void deleteProduct(){
        given().pathParams("id",17)
                .when().delete("/products/{id}")
                .then().log().status();
    }


}
