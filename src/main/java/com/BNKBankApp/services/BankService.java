package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.model.Bank;
import com.BNKBankApp.data.repository.AccountRepository;
import com.BNKBankApp.data.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    AccountRepository accountRepository;


    public void createAccount(Bank bank, Account account) {
        bankRepository.save(bank);
        accountRepository.save(account);
    }
}
