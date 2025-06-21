package com.BNKBankApp.dtos;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {

    private String token;
    private String id;
    private String username;

}
