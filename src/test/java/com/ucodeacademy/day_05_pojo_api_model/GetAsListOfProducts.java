package com.ucodeacademy.day_05_pojo_api_model;

import com.ucodeacademy.pojo.Products;
import com.ucodeacademy.utilities.FruitShopUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetAsListOfProducts extends FruitShopUtil {

    @Test
    public void getAllProductsAsListOfProduct(){
        Response response = given().contentType(ContentType.JSON)
                .when()
                .get("/products");

        response.then().assertThat().statusCode(200);
        // create object of JsonPath
        JsonPath jsonPath = response.jsonPath();

        // create list of product and store all products
        List<Map<String, Object>> allProduct = new ArrayList<>();

        int totalProduct = jsonPath.getList("products").size();

        for (int i=0; i< totalProduct; i++){
            allProduct.add(jsonPath.get("products["+i+"]"));
        }




       System.out.println(allProduct);
        response.prettyPrint();
    }
}
