package com.BNKBankApp.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponse {

    private String id;
    private String fullName;
    private String email;
    private String userName;

}
