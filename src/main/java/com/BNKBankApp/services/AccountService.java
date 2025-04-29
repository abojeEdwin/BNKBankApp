package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    public void deleteAll() {
        accountRepository.deleteAll();
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void transfer(String fromAccountNumber,String toAccountNumber, double amount) {
        Account from = accountRepository.findByAccountNumber(fromAccountNumber);
        Account to = accountRepository.findByAccountNumber(toAccountNumber);
        from.setBalance(from.getBalance()-amount);
        System.out.println(from.getBalance());
        to.setBalance(to.getBalance()+amount);
        System.out.println(to.getBalance());
        accountRepository.save(from);
        accountRepository.save(to);
    }

}
