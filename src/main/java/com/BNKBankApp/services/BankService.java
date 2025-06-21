package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.model.Bank;
import com.BNKBankApp.data.model.CardDetails;
import com.BNKBankApp.data.model.CardType;
import com.BNKBankApp.data.repository.AccountRepository;
import com.BNKBankApp.data.repository.BankRepository;
import com.BNKBankApp.data.repository.CardDetailsRepository;
import com.BNKBankApp.dtos.CardDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardDetailsRepository cardDetailsRepository;

    @Autowired
    GenerateAccountNumberService generateAccountNumberService;

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void createAccount(Bank bank, Account account) {
        bankRepository.save(bank);
        String newAccountNumber = GenerateAccountNumberService.generateAccountNumber();
        String hashTransactionPassword = hashPassword(account.getTransactionPin());
        account.setTransactionPin(hashTransactionPassword);
        account.setAccountNumber(newAccountNumber);
        accountRepository.save(account);
    }

    public void createCard(String accountNumber,CardType cardType) {
        CardDetails cardDetails = new CardDetails();
        cardDetails.setCardNumber(GenerateCardNumber.generateAccountNumber());
        cardDetails.setCardType(CardType.MASTERCARD);
        cardDetails.setCvv(GenerateCardNumber.generateAccountCvv());
        cardDetails.setExpiryDate(LocalDate.EPOCH.plusYears(5));
        cardDetailsRepository.save(cardDetails);

        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setCardDetails(cardDetails);
        accountRepository.save(account);
    }


    public static String hashPassword(String password){
        return passwordEncoder.encode(password);
    }
}
