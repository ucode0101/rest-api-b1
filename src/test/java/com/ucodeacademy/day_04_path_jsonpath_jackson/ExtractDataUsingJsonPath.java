package com.ucodeacademy.day_04_path_jsonpath_jackson;

import com.ucodeacademy.utilities.FruitShopUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ExtractDataUsingJsonPath extends FruitShopUtil {

    @Test
    public void getAllProducts(){

        Response response = given().contentType(ContentType.JSON)
                .when()
                .get("/products");

        // verify status
        response.then().assertThat().statusCode(200);

        // 1st way to create an instance/object of JsonPath
        JsonPath jsonPath = response.jsonPath();

        // another to call jsonPath() and call other method
        // It is better to follow 1st
        //int id = response.jsonPath().getInt("products[0].id");

        // get product id (any id) using jsonPath
        int productId = jsonPath.getInt("products[2].id");
        System.out.println("Product id: "+productId);

        System.out.println("========================================");

        // get product name (any name) using jsonPath
        String productName = jsonPath.getString("products[2].name");

        System.out.println("Product name: "+productName);

        System.out.println("========================================");

        // get all product ids as List (Collection)
        List<Integer> allIds = jsonPath.getList("products.id");
        System.out.println("All Ids: "+allIds);

        System.out.println("========================================");

        // get all products names as List (Collection)
        List<String> allNames = jsonPath.getList("products.name");
        System.out.println("All names: "+allNames);

        System.out.println("========================================");

        // get all products as List of Maps
        List<Map<String, Object>> allProducts = jsonPath.getList("products");
        System.out.println("All products: "+ allProducts);

        System.out.println("========================================");

        response.prettyPrint();

        // create an instance of XMLPath
        //XmlPath xmlPath = response.xmlPath();




    }
}
