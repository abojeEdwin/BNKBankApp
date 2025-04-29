package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.junit.jupiter.api.Assertions.*;




@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void saveUserTest(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setFullName("fullName");
        user.setPhone("phone");
        userRepository.save(user);
    }

    @Test
    public void findByUsernameTest(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setFullName("fullName");
        user.setPhone("phone");
        userRepository.save(user);
        User foundUser = userRepository.findByUsername("username");
        assertNotNull(foundUser);
    }

}