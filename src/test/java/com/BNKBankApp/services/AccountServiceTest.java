package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.model.Bank;
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

    @Autowired
    BankService bankService;

//    @BeforeEach
//    void setUp() {
//        accountService.deleteAll();
//    }
//
//    @AfterEach
//    void tearDown() {
//        accountService.deleteAll();
//    }

    @Test
    public void transferTest(){
        Bank bank = new Bank();
        bank.setSwiftCode("SWIFT");
        bank.setBankName("Bank Name");

        Account account1 = new Account();
        account1.setBalance(500);
        account1.setTransactionPin("1234");
        bankService.createAccount(bank, account1);

        Account account2 = new Account();
        account2.setBalance(500);
        account2.setTransactionPin("5678");
        bankService.createAccount(bank, account2);

        assertEquals(account1.getBalance(), account2.getBalance());

    }

    @Test
    public void transferTest2(){
        accountService.transfer("5566290782", "0537763218", 500,"1234");
        assertEquals(1000, accountRepository.findByAccountNumber("0537763218").getBalance());
        assert accountRepository.findByAccountNumber("5566290782").getBalance() == 0.0;
    }

}