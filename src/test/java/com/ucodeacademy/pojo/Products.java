package com.ucodeacademy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

// this annotation is used to ignore any field/property that I don't have matching instance variable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {

    // instance variable that match with json values, including name, and data type
    private String name;
    private double price;
    private int id;

    private List<Products> allProducts;





    //Constructor with arg
    public Products(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    // No arg Constructor
    public Products() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   @JsonIgnore  // this annotation is used to ignore it when doing deserialization
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //@JsonIgnore // this annotation is used to ignore it when doing deserialization
    public List<Products> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Products> allProducts) {
        this.allProducts = allProducts;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }

}
