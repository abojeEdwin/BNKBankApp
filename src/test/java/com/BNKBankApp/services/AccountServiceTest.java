package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService.deleteAll();
    }

    @AfterEach
    void tearDown() {
        accountService.deleteAll();
    }

    @Test
    public void transferTest(){
        Account account1 = new Account();
        Account account2 = new Account();

        account1.setTransactionPin("1234");
        account1.setBalance(100000);
        account1.setAccountNumber("1234");
        accountService.saveAccount(account1);

        account2.setTransactionPin("5678");
        account2.setBalance(0);
        account2.setAccountNumber("5678");
        Account savedAccount = accountService.saveAccount(account2);
        accountService.transfer("1234","5678",5000);
        System.out.println(account2.getBalance());
    }

}