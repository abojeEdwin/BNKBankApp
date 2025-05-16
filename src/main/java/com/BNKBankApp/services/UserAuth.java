package com.BNKBankApp.services;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.data.repository.UserRepository;
import com.BNKBankApp.dtos.UserLoginRequest;
import com.BNKBankApp.dtos.UserLoginResponse;
import com.BNKBankApp.dtos.UserRegisterResponse;
import com.BNKBankApp.exceptions.*;
import com.BNKBankApp.security.HashingPassword;
import com.BNKBankApp.security.VerifyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserAuth {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    OtpService otpService;

    @Autowired
    HashingPassword hashingPassword;

    @Autowired
    VerifyEmail verifyEmail;




    public UserRegisterResponse register(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {throw new DuplicateEmailException("Email already exist");}
        if(userRepository.existsByUsername(user.getUsername())) {throw new DuplicateUserNameException("Username already exist");}
        if(!verifyEmail.isVerifiedEmail(user.getEmail())) {throw new InvalidEmailException("Invalid email, please enter a valid email");}
        String hashedPassword = hashingPassword.hashPassword(user.getPassword());
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

    public UserLoginResponse login(UserLoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(user == null) {throw new UserNotFoundException("User not found");}
        if(!hashingPassword.verifyPassword(user.getPassword(), loginRequest.getPassword())) {throw new InvalidPasswordException("Incorrect password, please enter a valid password");}
        otpService.verifyOTPAndGenerateToken(user.getEmail(),loginRequest.getOtp());
        String token = jwtService.generateToken(user.getUsername());
        return new UserLoginResponse(token,user.getId(),user.getUsername());
    }
}
