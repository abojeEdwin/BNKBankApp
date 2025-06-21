package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.model.Bank;
import com.BNKBankApp.data.model.CardType;
import com.BNKBankApp.data.repository.BankRepository;
import com.BNKBankApp.dtos.CardDetailsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankServiceTest {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankService bankService;

    @Autowired
    private GenerateAccountNumberService generateAccountNumberService;

    @BeforeEach
    void setUp(){
        bankRepository.deleteAll();
    }


    @AfterEach
    void tearDown(){
        bankRepository.deleteAll();
    }

    @Test
    public void createAccountTest(){
        Bank bank = new Bank();
        bank.setBankName("Union UTC Bank");
        bank.setSwiftCode("UNI04BN");
        Account account = new Account();
        account.setBalance(100.0);
        account.setTransactionPin("0000");
        bankService.createAccount(bank,account);
        assertTrue(bankRepository.existsById(bank.getId()));
    }

    @Test
    public void createCreditCardTest(){
        Bank bank = new Bank();
        bank.setBankName("Union UTC Bank");
        bank.setSwiftCode("UNI04BN");
        Account account = new Account();
        account.setBalance(100.0);
        account.setTransactionPin("0000");
        bankService.createAccount(bank,account);
        CardDetailsResponse response = bankService.createCard("0480111800", CardType.VERVE);
        assertEquals("Successfully created card", response.getMessage());
    }



}