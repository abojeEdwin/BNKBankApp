package com.BNKBankApp.services;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service
public class GenerateAccountNumberService {


        private static final int ACCOUNT_NUMBER_LENGTH = 10;
        private static final String DIGITS = "0123456789";
        private static final SecureRandom secureRandom = new SecureRandom();

        public static String generateAccountNumber() {
            StringBuilder accountNumber = new StringBuilder(ACCOUNT_NUMBER_LENGTH);
            for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
                int randomIndex = secureRandom.nextInt(DIGITS.length());
                accountNumber.append(DIGITS.charAt(randomIndex));
            }
            return accountNumber.toString();
        }

    }

