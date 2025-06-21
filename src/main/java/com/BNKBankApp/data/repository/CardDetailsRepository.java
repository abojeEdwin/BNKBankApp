package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.CardDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CardDetailsRepository extends MongoRepository<CardDetails, String> {
    CardDetails findByAccountNumber(String accountNumber);

}
