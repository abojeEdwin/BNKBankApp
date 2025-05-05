package com.BNKBankApp.security;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class VerifyEmail {

    private static String EMAIL_REGEX =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";


    public boolean isVerifiedEmail(String email) {
        return Pattern.compile(EMAIL_REGEX)
                .matcher(email).
                matches();
    }

}
