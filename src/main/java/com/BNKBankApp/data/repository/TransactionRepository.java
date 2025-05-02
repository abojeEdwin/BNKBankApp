package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
