package com.BNKBankApp.data.model;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@Document(collection="CardDetails")
public class CardDetails {
    @NotNull(message = "This field cannot be blank")
    private String Id;
    @NotNull(message = "This field cannot be blank")
    private String cardNumber;
    @NotNull(message = "This field cannot be blank")
    private String cvv;
    @NotNull(message = "This field cannot be blank")
    private LocalDate expiryDate;
    @NotNull(message = "This field cannot be blank")
    private CardType cardType;
}
