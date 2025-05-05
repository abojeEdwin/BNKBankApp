package com.BNKBankApp.data.model;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class TransactionMessage {

    private String content;
    private String timestamp;
}
