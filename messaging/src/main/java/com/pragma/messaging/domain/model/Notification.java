package com.pragma.messaging.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private Long idNotification;
    private LocalDateTime dateNotification;
    private String phoneNumber;
    private Long idOrder;
    private String pin;
    private StatusPin statusPin;
    private String message;
    private boolean isSent;
}
