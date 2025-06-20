package com.BNKBankApp.dtos;
import lombok.*;

@Getter
@Setter
public class UserLoginResponse {

    private String token;
    private String id;
    private String username;

}
