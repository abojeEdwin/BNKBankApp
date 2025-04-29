package com.BNKBankApp.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponse {
    private String id;
    private String fullName;
    private String email;
    private String userName;

}
