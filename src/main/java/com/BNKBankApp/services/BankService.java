package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.model.Bank;
import com.BNKBankApp.data.repository.AccountRepository;
import com.BNKBankApp.data.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    GenerateAccountNumberService generateAccountNumberService;

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void createAccount(Bank bank, Account account) {
        bankRepository.save(bank);
        String newAccountNumber = generateAccountNumberService.generateAccountNumber();
        String hashTransactionPassword = hashPassword(account.getTransactionPin());
        account.setTransactionPin(hashTransactionPassword);
        account.setAccountNumber(newAccountNumber);
        accountRepository.save(account);
    }


    public static String hashPassword(String password){
        return passwordEncoder.encode(password);
    }
}
