package com.BNKBankApp.dtos;
import lombok.Data;

@Data
public class UserLoginRequest {

    private String email;
    private String password;
    private String otp;


}
