package com.BNKBankApp.data.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document(collection="Transaction")
@Data
public class Transaction {
    @Id
    String id;
    @NotBlank(message="This field is required")
    String transactionType;
    @NotEmpty(message="This field cannot be empty")
    String recipient;
    @NotBlank(message="This field cannot be empty")
    String description;
    @NotNull
    double amount;
    @NotEmpty
    String balance;
    @NotNull
    Instant timestamp;

}
