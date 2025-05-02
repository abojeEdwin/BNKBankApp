package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.model.Transaction;
import com.BNKBankApp.data.repository.AccountRepository;
import com.BNKBankApp.data.repository.TransactionRepository;
import com.BNKBankApp.exceptions.InvalidBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void deleteAll() {
        accountRepository.deleteAll();
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void transfer(String fromAccountNumber,String toAccountNumber, double amount,String senderTransactionPin) {
        Account from = accountRepository.findByAccountNumber(fromAccountNumber);
        Account to = accountRepository.findByAccountNumber(toAccountNumber);
        if(from.getBalance() >= amount) {
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            accountRepository.save(from);
            accountRepository.save(to);
        }
        else{throw new InvalidBalanceException("Insufficient account balance");}

        Transaction transaction = new Transaction();
        transaction.setTransactionType("transfer_out");
        transaction.setAmount(amount);
        transaction.setRecipient(fromAccountNumber);
        String accountBalance = String.valueOf(from.getBalance());
        transaction.setBalance(accountBalance);
        Instant instant = Instant.now();
        transaction.setTimestamp(instant);
        transactionRepository.save(transaction);

        Transaction recieverTransaction = new Transaction();
        recieverTransaction.setTransactionType("transfer_in");
        recieverTransaction.setAmount(amount);
        recieverTransaction.setRecipient(toAccountNumber);
        recieverTransaction.setTimestamp(instant);
        String recieverAccountBalance = String.valueOf(to.getBalance());
        recieverTransaction.setBalance(recieverAccountBalance);
        transactionRepository.save(recieverTransaction);

    }

}
