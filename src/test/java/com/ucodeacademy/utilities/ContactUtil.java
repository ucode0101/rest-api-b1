package com.ucodeacademy.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;

public class ContactUtil {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
    }

    public static String generateToken(String email, String password){
        String endpoint = "https://thinking-tester-contact-list.herokuapp.com/users/login";
        String credentials = """
                {
                "email" :"%s",
                "password":"%s"
                }
                """.formatted(email,password);

        Response response = given().contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post(endpoint);

         return response.path("token");

    }




}
