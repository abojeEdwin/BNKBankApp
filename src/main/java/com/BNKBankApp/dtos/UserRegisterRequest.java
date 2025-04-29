package com.BNKBankApp.dtos;
import lombok.Data;



@Data
public class UserRegisterRequest {

    private String userName;
    private String password;
    private String email;
    private String phone;
    private String fullName;
}
