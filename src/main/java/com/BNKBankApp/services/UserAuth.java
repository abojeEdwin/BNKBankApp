package com.BNKBankApp.services;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.data.repository.AccountRepository;
import com.BNKBankApp.data.repository.UserRepository;
import com.BNKBankApp.dtos.UserLoginRequest;
import com.BNKBankApp.dtos.UserLoginResponse;
import com.BNKBankApp.dtos.UserRegisterResponse;
import com.BNKBankApp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;


@Service
public class UserAuth {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    OtpService otpService;

    private static String EMAIL_REGEX =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserRegisterResponse register(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {throw new DuplicateEmailException("Email already exist");}
        if(userRepository.existsByUsername(user.getUsername())) {throw new DuplicateUserNameException("Username already exist");}
        if(!isVerifiedEmail(user.getEmail())) {throw new InvalidEmailException("Invalid email, please enter a valid email");}
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        return new UserRegisterResponse(savedUser.getId(),savedUser.getEmail(),savedUser.getFullName(),savedUser.getPhone());
    }


    public long count() {
        return userRepository.count();
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public boolean isVerifiedEmail(String email) {
        return Pattern.compile(EMAIL_REGEX)
                .matcher(email).
                matches();
    }

    public UserLoginResponse login(UserLoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(user == null) {throw new UserNotFoundException("Invalid password");}
        if(!verifyPassword(user.getPassword(), loginRequest.getPassword())) {throw new InvalidEmailException("Incorrect password, please enter a valid password");}
        verifyOTPAndGenerateToken(user.getEmail(),loginRequest.getOtp());
        String token = jwtService.generateToken(user.getUsername());
        return new UserLoginResponse(token,user.getId(),user.getUsername());
    }

    public static String hashPassword(String password){
        return passwordEncoder.encode(password);
    }


    public static boolean verifyPassword(String hashedPassword, String inputPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty() || inputPassword == null || inputPassword.isEmpty()) {return false;}
        try {return passwordEncoder.matches(inputPassword, hashedPassword);} catch (IllegalArgumentException e) {return false;}}

    public String verifyOTPAndGenerateToken(String email, String otp) {
        if (otpService.validateOTP(email, "login", otp)) {
            return jwtService.generateToken(email);
        }
        throw new SecurityException("Invalid OTP");
    }
}
