package com.ucodeacademy.utilities;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class FruitShopUtil {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://fruitshop2-predic8.azurewebsites.net/shop/v2";
    }
}
