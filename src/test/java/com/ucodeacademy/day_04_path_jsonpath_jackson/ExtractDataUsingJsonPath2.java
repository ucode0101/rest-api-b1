package com.ucodeacademy.day_04_path_jsonpath_jackson;

import com.ucodeacademy.utilities.FruitShopUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExtractDataUsingJsonPath2 extends FruitShopUtil {


    @Test
    public void testJsonPath(){
        Response response = given().contentType(ContentType.JSON)
                .when()
                .get("/products");

        Assert.assertEquals(200, response.getStatusCode());

        // create an object/instance of JsonPath
        JsonPath jsonPath = response.jsonPath();

        // get product id using get()
        int id = jsonPath.get("products[0].id");

        // get product id using getInt()
        int id2 = jsonPath.getInt("products[0].id");

        System.out.println(id);
        System.out.println(id2);

        // get product name using get()
        String name1 = jsonPath.get("products[1].name");

        // get product name using getString()
        String name2 = jsonPath.getString("products[1].name");

        System.out.println(name1);
        System.out.println(name2);




    }
}
