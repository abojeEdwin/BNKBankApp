package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByAccountNumber(String accountNumber);
    Account findByEmail(String email);
    Account findByUsername(String username);
    Boolean existsByAccountNumber(String accountNumber);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
