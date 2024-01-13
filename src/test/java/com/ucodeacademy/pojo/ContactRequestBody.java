package com.ucodeacademy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // for getters, setter, toString
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestBody {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;


}
