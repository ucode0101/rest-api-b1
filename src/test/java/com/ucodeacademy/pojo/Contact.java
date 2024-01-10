package com.ucodeacademy.pojo;

import lombok.*;

@Getter // This annotation adds getters for all field/instance variables
@Setter // This annotation adds setters for all field/instance variables
@AllArgsConstructor // This annotation adds all argument Constructor
@NoArgsConstructor // This annotation adds no argument constructor
@ToString // This annotation add/overrides toString() method

public class Contact {

    private String firstName;
    private String lastName;
    private String birthdate;
    private String email;
    private String phone;
    private String street1;
    private String street2;
    private String stateProvince;
    private String postalCode;
    private String country;

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getBirthdate() {
//        return birthdate;
//    }
//
//    public void setBirthdate(String birthdate) {
//        this.birthdate = birthdate;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getStreet1() {
//        return street1;
//    }
//
//    public void setStreet1(String street1) {
//        this.street1 = street1;
//    }
//
//    public String getStreet2() {
//        return street2;
//    }
//
//    public void setStreet2(String street2) {
//        this.street2 = street2;
//    }
//
//    public String getStateProvince() {
//        return stateProvince;
//    }
//
//    public void setStateProvince(String stateProvince) {
//        this.stateProvince = stateProvince;
//    }
//
//    public String getPostalCode() {
//        return postalCode;
//    }
//
//    public void setPostalCode(String postalCode) {
//        this.postalCode = postalCode;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public Contact(String firstName, String lastName, String birthdate, String email,
//                   String phone, String street1, String street2, String stateProvince,
//                   String postalCode, String country) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.birthdate = birthdate;
//        this.email = email;
//        this.phone = phone;
//        this.street1 = street1;
//        this.street2 = street2;
//        this.stateProvince = stateProvince;
//        this.postalCode = postalCode;
//        this.country = country;
//    }
//
//    public Contact() {
//    }
//
//    @Override
//    public String toString() {
//        return "Contact{" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", birthdate='" + birthdate + '\'' +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", street1='" + street1 + '\'' +
//                ", street2='" + street2 + '\'' +
//                ", stateProvince='" + stateProvince + '\'' +
//                ", postalCode='" + postalCode + '\'' +
//                ", country='" + country + '\'' +
//                '}';
//    }
}
