package com.ucodeacademy.day_02_path_query_param_tokens;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddNewContact {

    static String token = null;

    @BeforeClass
    public static void setUp(){
        baseURI = "https://thinking-tester-contact-list.herokuapp.com/contacts";
        token = GenerateToken.loginToGetToken();
    }

    String newContact = """
            {
                "firstName": "John",
                "lastName": "Doe",
                "birthdate": "1970-01-01",
                "email": "jdoe@fake.com",
                "phone": "8005555555",
                "street1": "1 Main St.",
                "street2": "Apartment A",
                "city": "Anytown",
                "stateProvince": "KS",
                "postalCode": "12345",
                "country": "USA"
            }
            """;

    @Test
    public void addNewContact(){
        Response response = given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(newContact)
                .when()
                .post(baseURI);

        Assert.assertEquals(201, response.statusCode());

        String contactID = response.path("_id");

        response.prettyPrint();

    }
}
