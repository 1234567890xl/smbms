package com.xl.smbms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String userName;
    private String userCode;
    private String userPassword;
    private Date birthday;
    private int age;
    private int gender;
    private String phone;
    private String address;
    private  int userRole;
    private  String roleName;
    private Role role ;

}
