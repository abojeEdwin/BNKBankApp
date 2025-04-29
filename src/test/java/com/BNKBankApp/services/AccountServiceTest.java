package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

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
        account1.setTransactionPin("1234");
        account1.setBalance(1000.0);
        account1.setAccountNumber("1234");
        account1.setTransactionPin("0000");
        Account savedAccount1 = accountService.saveAccount(account1);


        Account account2 = new Account();
        account2.setTransactionPin("5678");
        account2.setBalance(0);
        account2.setAccountNumber("5678");
        account2.setTransactionPin("1111");
        Account savedAccount = accountService.saveAccount(account2);

        assert savedAccount1.getBalance() == 1000.0;
        assert savedAccount.getBalance() == 0.0;

        accountService.transfer("1234","5678",500,"0000");

        assertEquals(500,accountRepository.findByAccountNumber("1234").getBalance());

    }

}