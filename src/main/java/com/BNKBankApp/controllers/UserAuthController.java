package com.BNKBankApp.controllers;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.dtos.UserLoginRequest;
import com.BNKBankApp.dtos.UserLoginResponse;
import com.BNKBankApp.dtos.UserRegisterResponse;
import com.BNKBankApp.services.UserAuth;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api")
public class UserAuthController {

    @Autowired
    UserAuth userAuth;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody User user){
        UserRegisterResponse savedUser = userAuth.register(user);
        if(savedUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest){
        UserLoginResponse savedUser = userAuth.login(userLoginRequest);
        if(savedUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }



}
