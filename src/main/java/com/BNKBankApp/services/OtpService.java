package com.BNKBankApp.services;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.email.from}")
    private String fromEmail;

    private Map<String, String> otpStorage = new ConcurrentHashMap<>();

    public String generateOTP() {
        return RandomStringUtils.randomNumeric(6);
    }

    public void sendOTPEmail(String email, String purpose) {
        String otp = generateOTP();
        otpStorage.put(email + ":" + purpose, otp);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("Your OTP for " + purpose);
        message.setText("Your OTP is: " + otp + "\nThis OTP is valid for 5 minutes.");
        mailSender.send(message);
    }

    public boolean validateOTP(String email, String purpose, String otp) {
        String key = email + ":" + purpose;
        String storedOtp = otpStorage.get(key);
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpStorage.remove(key);
            return true;
        }
        return false;
    }



}
