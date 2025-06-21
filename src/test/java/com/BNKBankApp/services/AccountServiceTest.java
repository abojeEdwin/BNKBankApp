package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.model.Bank;
import com.BNKBankApp.data.model.CardType;
import com.BNKBankApp.data.repository.AccountRepository;
import com.BNKBankApp.data.repository.BankRepository;
import com.BNKBankApp.data.repository.CardDetailsRepository;
import com.BNKBankApp.dtos.CardDetailsResponse;
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
    CardDetailsRepository cardDetailsRepository;
    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankService bankService;

//    @BeforeEach
//    void setUp() {
//       accountService.deleteAll();
//       cardDetailsRepository.deleteAll();
//       bankRepository.deleteAll();
//    }
//
//    @AfterEach
//    void tearDown() {
//        accountService.deleteAll();
//        cardDetailsRepository.deleteAll();
//        bankRepository.deleteAll();
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
        accountService.transfer("8757378735", "0480111800", 200,"5678");
        assertEquals(200, accountRepository.findByAccountNumber("0480111800").getBalance());
        assert accountRepository.findByAccountNumber("8757378735").getBalance() == 800.0;

    }
    @Test
    public void findAccountByCardNumberTest(){

//        CardDetailsResponse response = bankService.createCard("6912957462", CardType.VERVE);
        assertEquals("6912957462",accountService.findAccountByCardNumber("1986060720525362").getAccountNumber());

    }

}