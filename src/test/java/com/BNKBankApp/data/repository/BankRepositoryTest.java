package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Bank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.junit.jupiter.api.Assertions.*;



@DataMongoTest
class BankRepositoryTest {

    @Autowired
    private BankRepository bankRepository;

    @BeforeEach
    void setUp() {
        bankRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        bankRepository.deleteAll();
    }

    @Test
    public void saveBankTest() {
        Bank bank = new Bank();
        bank.setBankName("Unity Revolution");
        bank.setSwiftCode("UNI04R");
        Bank savedBank = bankRepository.save(bank);
        assert bankRepository.count() == 1;
    }


}