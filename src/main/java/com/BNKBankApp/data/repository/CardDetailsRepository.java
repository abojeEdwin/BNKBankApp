package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.CardDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CardDetailsRepository extends MongoRepository<CardDetails, String> {

    Optional <CardDetails> findByCardNumber(String cardNumber);

}
