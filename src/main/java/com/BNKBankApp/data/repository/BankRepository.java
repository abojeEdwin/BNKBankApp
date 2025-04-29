package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends MongoRepository<Bank, String> {

    Bank findByBankName(String name);

}
