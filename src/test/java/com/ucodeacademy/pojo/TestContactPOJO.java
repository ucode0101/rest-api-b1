package com.ucodeacademy.pojo;

import com.ucodeacademy.utilities.ContactUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestContactPOJO extends ContactUtil {

    String token = generateToken("xyz@test.com", "testing123");

    @Test
    public void addNewContact(){

        ContactBody contact = new ContactBody();
        contact.setFirstName("James");
        contact.setLastName("John");
        contact.setEmail("jj@test.com");
        contact.setPhone("1922222222");

        Response response = given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(contact)
                .when()
                .post("/contacts");

        response.then().statusCode(201);

        // verify contact ID
        String id = response.jsonPath().getString("_id");
        Assert.assertTrue(!id.isEmpty());

        // verify first name
        String actualFirstName = response.jsonPath().getString("firstName");
        Assert.assertEquals(contact.getFirstName(), actualFirstName);

        // verify last name
        String actualLastName = response.jsonPath().getString("lastName");
        Assert.assertEquals(contact.getLastName(), actualLastName);
        response.prettyPrint();

    }

    @Test
    public void getContactById(){


        String contactID = "658396d33169cf00130e6ad4";

        Response response = given().auth().oauth2(token)
                .accept(ContentType.JSON)
                //.pathParams("id",contactID)
                .when()
                .get("/contacts/"+contactID);

        // store response body in Contact object

        Contact contact1 = response.as(Contact.class);

        // print first & last name
        System.out.println(contact1.getFirstName());
        System.out.println(contact1.getLastName());

        System.out.println("=================================");
       System.out.println(contact1);

        //response.prettyPrint();

    }


    @Test
    public void getAllContacts(){
        // get all contact and store them in list of Contact

        Response response = given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when()
                .get("/contacts");

        List<Contact>  allContact = response.jsonPath().getList("",Contact.class);

       // loop and print all first and last names
        for (Contact eachContact : allContact){
            System.out.println(eachContact.getFirstName());
            System.out.println(eachContact.getLastName());
        }

        //System.out.println(allContact);

       // response.prettyPrint();

        System.out.println("=========================================");

        // delete 5 contact by ID
        // a new DELETE Request
        for (int i =0; i< 5; i++){
            response = given().auth().oauth2(token)
                    .with()
                    .delete("/contacts/"+ allContact.get(i).get_id());

            response.prettyPrint();
        }


    }





}