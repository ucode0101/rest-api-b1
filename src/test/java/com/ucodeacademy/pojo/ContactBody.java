package com.ucodeacademy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactBody {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;


}
