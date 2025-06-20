package com.BNKBankApp.dtos;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterResponse {
    private String id;
    private String fullName;
    private String email;
    private String userName;

}
