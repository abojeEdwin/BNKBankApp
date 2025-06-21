package com.BNKBankApp.services;
import com.BNKBankApp.data.model.Account;
import com.BNKBankApp.data.model.Bank;
import com.BNKBankApp.data.model.CardDetails;
import com.BNKBankApp.data.model.CardType;
import com.BNKBankApp.data.repository.AccountRepository;
import com.BNKBankApp.data.repository.BankRepository;
import com.BNKBankApp.data.repository.CardDetailsRepository;
import com.BNKBankApp.dtos.CardDetailsResponse;
import com.BNKBankApp.exceptions.AccountNotFoundException;
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

    public CardDetailsResponse createCard(String accountNumber,CardType cardType) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if(account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        String message = "Successfully created card";
        CardDetails cardDetails = new CardDetails();
        cardDetails.setCardNumber(GenerateCardNumber.generateAccountNumber());
        cardDetails.setCardType(CardType.MASTERCARD);
        cardDetails.setCvv(GenerateCardNumber.generateAccountCvv());
        cardDetails.setExpiryDate(LocalDate.EPOCH.plusYears(5));
        cardDetails.setAccount(account);
        CardDetails savedCard = cardDetailsRepository.save(cardDetails);

        account.setCardDetailsId(savedCard.getId());
        accountRepository.save(account);

        return new CardDetailsResponse(savedCard.getId(),savedCard.getCardNumber(),message);

    }


    public static String hashPassword(String password){
        return passwordEncoder.encode(password);
    }
}
