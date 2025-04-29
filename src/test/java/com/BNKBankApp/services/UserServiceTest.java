package com.BNKBankApp.services;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.dtos.UserLoginRequest;
import com.BNKBankApp.dtos.UserLoginResponse;
import com.BNKBankApp.exceptions.DuplicateEmailException;
import com.BNKBankApp.exceptions.DuplicateUserNameException;
import com.BNKBankApp.exceptions.UserNotFoundException;
import com.BNKBankApp.exceptions.InvalidEmailException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;




@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        userService.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }

    @Test
    public void registerUser() {
        User user = new User();
        user.setFullName("Full name");
        user.setPhone("+990087311");
        user.setPassword("password");
        user.setEmail("abojeedwin@gmail.com");
        user.setUsername("User name");
        userService.register(user);
        assertEquals(1,userService.count());
    }

    @Test
    public void registerUserWithSameEmailTest(){
        User user = new User();
        user.setFullName("Full name");
        user.setPhone("+990087311");
        user.setPassword("password");
        user.setEmail("abojeedwin@gmail.com");
        user.setUsername("User name");
        userService.register(user);

        User user1 = new User();
        user1.setFullName("Full name");
        user1.setPhone("+990087311");
        user1.setPassword("password");
        user1.setEmail("abojeedwin@gmail.com");
        user1.setUsername("User name");
        assertThrows(DuplicateEmailException.class,()->userService.register(user1));
    }

    @Test
    public void registerUserWithDuplicateUsernameTest(){
        User user = new User();
        user.setFullName("Full name");
        user.setPhone("+990087311");
        user.setPassword("password");
        user.setEmail("abojeedwin@gmail.com");
        user.setUsername("User name");
        userService.register(user);

        User user1 = new User();
        user1.setFullName("Full name");
        user1.setPhone("+990087311");
        user1.setPassword("password");
        user1.setEmail("abojeedwin123@gmail.com");
        user1.setUsername("User name");
        assertThrows(DuplicateUserNameException.class,()->userService.register(user1));
    }

    @Test
    public void registerUserWithInvalidEmailTest(){
        User user = new User();
        user.setFullName("Full name");
        user.setPhone("+990087311");
        user.setPassword("password");
        user.setEmail("abojeedwingmail.com");
        user.setUsername("User name");
        assertThrows(InvalidEmailException.class,()->userService.register(user));
    }

    @Test
    public void loginUser() {
        User user = new User();
        user.setFullName("Full name");
        user.setPhone("+990087311");
        user.setPassword("password");
        user.setEmail("abojeedwin@gmail.com");
        user.setUsername("User name");
        userService.register(user);
        assertEquals(1,userService.count());

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("abojeedwin@gmail.com");
        loginRequest.setPassword("password");
        UserLoginResponse loginUser = userService.login(loginRequest);
        assertEquals(1,userService.count());
        assertEquals(loginUser.getUsername(),"User name");
    }

    @Test
    public void loginUserWithIncorrectPasswordTest(){
        User user = new User();
        user.setFullName("Full name");
        user.setPhone("+990087311");
        user.setPassword("password");
        user.setEmail("abojeedwin@gmail.com");
        user.setUsername("User name");
        userService.register(user);
        assertEquals(1,userService.count());

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("abojeedwin@gmail.com");
        loginRequest.setPassword("pasword");
        assertThrows(InvalidEmailException.class,()->userService.login(loginRequest));
    }

    @Test
    public void loginUserWithIncorrectEmailTest(){
        User user = new User();
        user.setFullName("Full name");
        user.setPhone("+990087311");
        user.setPassword("password");
        user.setEmail("abojeedwin@gmail.com");
        user.setUsername("User name");
        userService.register(user);
        assertEquals(1,userService.count());

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("abojeed@gmail.com");
        loginRequest.setPassword("password");
        assertThrows(UserNotFoundException.class,()->userService.login(loginRequest));


    }
}