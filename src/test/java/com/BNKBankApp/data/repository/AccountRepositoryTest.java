package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;


//    @BeforeEach
//    void setUp() {
//        accountRepository.deleteAll();
//    }
//
//    @AfterEach
//    void tearDown() {
//        accountRepository.deleteAll();
//    }

    @Test
    public void saveAccountTest() {
        Account account = new Account();
        account.setAccountNumber("123456789");
        account.setBalance(100.0);
        account.setTransactionPin("12343");
        accountRepository.save(account);
    }

    @Test
    public void findByAccountNumberTest(){
        Account account = new Account();
        account.setAccountNumber("123456789");
        account.setBalance(100.0);
        account.setTransactionPin("12343");
        accountRepository.save(account);
        Account account1 = accountRepository.findByAccountNumber("123456789");
        assertEquals("123456789", account1.getAccountNumber());
        }
}