package com.BNKBankApp.services;
import com.BNKBankApp.data.model.TransactionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class AlertService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendAccountAlert(String userId, String message) {
        messagingTemplate.convertAndSendToUser(
                userId,
                "/topic/account-alerts",
                new TransactionMessage(message, LocalDateTime.now().toString())
        );
    }
}
