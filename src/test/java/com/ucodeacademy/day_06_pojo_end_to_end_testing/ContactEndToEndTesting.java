package com.ucodeacademy.day_06_pojo_end_to_end_testing;

import com.ucodeacademy.pojo.ContactRequestBody;
import com.ucodeacademy.pojo.ContactResponseBody;
import com.ucodeacademy.utilities.ContactUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactEndToEndTesting extends ContactUtil {


    // generate token
    String token = generateToken("xyz@test.com", "testing123");

    // create an object from ContactRequestBody and initialize it
    ContactRequestBody requestBody = new ContactRequestBody
            ("Smith","Jackson","js@test.com","111115555");

    String contactID = "65a1dfbdb9b98e00133d9bc3";

    @Test // 1. add new contact
    public void addNewContact(){
        Response response = given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contacts");

        response.then().statusCode(201);

        ContactResponseBody responseBody = response.as(ContactResponseBody.class);

        // assert first name and last name

        Assert.assertEquals(responseBody.getFirstName(), requestBody.getFirstName());

        Assert.assertEquals(responseBody.getLastName(), requestBody.getLastName());


        System.out.println(responseBody);

       // response.prettyPrint();

    }

    @Test  // 2. get newly added contact by id
    public void getContactById(){

        Response response = given().auth().oauth2(token)
                .accept(ContentType.JSON)
                //.pathParams("id",contactID)
                .when()
                //.get("/contacts/{id}");
                        .get("/contacts/"+contactID);

        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test // 3. update newly added contact using put request
    public void updateContact(){
        // created a new object of ContactRequestBody for updating contact
        ContactRequestBody updateBody = new ContactRequestBody
                ("Sam","Jack","ss12@test.com","1111111111");

        Response response = given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when().put("/contacts/"+contactID);

        //verify status
        Assert.assertEquals(200, response.getStatusCode());

        // verify updated first and last name
        Assert.assertEquals(updateBody.getFirstName(), response.jsonPath().getString("firstName"));

        Assert.assertEquals(updateBody.getLastName(), response.jsonPath().getString("lastName"));

        response.prettyPrint();


    }

    // 4. Partial update newly updated contact email and last name
    @Test
    public void partialUpdate(){
        // First way by simply creating String
        String partialBody = """
                 {
                 "lastName": "Mark",
                 "email": "jmm@test.com"
                 }
                """;
        // Second way is to create Map
        Map<String, Object> partialBodyMap = new HashMap<>();
        partialBodyMap.put("lastName","Mark2");
        partialBodyMap.put("email","jmm@test2.com");

        // Third way create an object of ContactRequestBody


        Response response = given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(partialBodyMap)
                .when()
                .patch("/contacts/"+contactID);

        // verify status code
        response.then().assertThat().statusCode(200);

        response.prettyPrint();

    }


    // 5. Delete contact
    @Test
    public void deleteContact(){
        Response response = given().auth().oauth2(token)
                .accept(ContentType.JSON)
                .when()
                .delete("/contacts/"+contactID);

        // verify status code
        response.then().statusCode(200);

        response.prettyPrint();

    }

    // 1. Create an object of ContactResponseBody, and store all contacts

    @Test
    public void getAllContacts(){

        Response response = given().auth().oauth2(token)
                .accept(ContentType.JSON)
                .when()
                .get("/contacts");

        // verify status code is 200
        response.then().statusCode(200);

        // create List of ContactResponseBody and store response body

        List<ContactResponseBody> listContactResponse = response.jsonPath().getList("", ContactResponseBody.class);
        //System.out.println(list);

        // loop and verify each contact id
        for (ContactResponseBody each : listContactResponse){

            // verify each id is not empty
            Assert.assertFalse(each.get_id().isEmpty());
           // System.out.println(each.get_id());

            // verify each firstName is not empty
            Assert.assertTrue(!each.getFirstName().isEmpty());
            //System.out.println(each.getFirstName());

            // verify each lastName is not blank
            Assert.assertTrue(!each.getLastName().isBlank());
           // System.out.println(each.getLastName());

        }
        System.out.println("===========================================");

        // create an object and store one contact from listContactResponse in it
        ContactResponseBody oneContact = listContactResponse.get(4);

        // print contact
        System.out.println(oneContact);

        // print contact firstName
        System.out.println(oneContact.getFirstName());

        // print contact lastName
        System.out.println(oneContact.getLastName());

        // extract any contact id from listContactResponse

        String id = listContactResponse.get(7).get_id();
        System.out.println(id);

        System.out.println("=======================================");

        // get one object from response body
        ContactResponseBody contact2 = response.jsonPath().getList("",ContactResponseBody.class).get(3);
        System.out.println("Contact2 "+contact2);



    }



}
