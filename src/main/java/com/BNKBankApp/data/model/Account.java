package com.BNKBankApp.data.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection= "Account")
public class Account {

    @Id
    private String id;
    @NotBlank
    private double balance;
    @NotBlank @NotBlank(message = "This field is required")
    private String accountNumber;
    @NotNull(message="This field cannot be empty")
    private String transactionPin;

}
