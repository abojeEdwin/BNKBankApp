package com.BNKBankApp.services;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service
public class GenerateCardNumber {


    private static final int ACCOUNT_NUMBER_LENGTH = 16;
    private static final String DIGITS = "0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int CVV_LENGTH = 3;


    public static String generateAccountNumber() {
        StringBuilder cardNumber = new StringBuilder(ACCOUNT_NUMBER_LENGTH);
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(DIGITS.length());
            cardNumber.append(DIGITS.charAt(randomIndex));
        }
        return cardNumber.toString();
    }

    public static String generateAccountCvv(){
        StringBuilder cvvNumber = new StringBuilder(CVV_LENGTH);
        for(int i = 0; i < CVV_LENGTH; i++){
            int newRandomIndex = secureRandom.nextInt(DIGITS.length());
            cvvNumber.append(DIGITS.charAt(newRandomIndex));
        }
        return cvvNumber.toString();
    }

}
