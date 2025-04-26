package com.BNKBankApp.data.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bank")
@Data
public class Bank {
    @Id
    private String id;
    @NotBlank @NotEmpty(message="This field cannot be empty")
    private String bankName;
    @NotBlank @NotEmpty(message="This field is required")
    private String swiftCode;
}
