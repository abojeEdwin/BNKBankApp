package com.BNKBankApp.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class HashingPassword {


    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static String hashPassword(String password){
        return passwordEncoder.encode(password);
    }


    public static boolean verifyPassword(String hashedPassword, String inputPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty() || inputPassword == null || inputPassword.isEmpty()) {return false;}
        try {return passwordEncoder.matches(inputPassword, hashedPassword);} catch (IllegalArgumentException e) {return false;}}

}
