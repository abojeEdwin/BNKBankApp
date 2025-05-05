package com.BNKBankApp.controllers;
import com.BNKBankApp.data.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class TransferController {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    private Transaction notifyDebit;
    private Transaction notifyCredit;

    public void notifyDebit(Transaction transaction) {
        this.notifyDebit = transaction;
        messagingTemplate.convertAndSend("/topic/notifyDebit", notifyDebit, new );

    }
}
