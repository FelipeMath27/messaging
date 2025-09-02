package com.pragma.messaging.application.dto;

import com.pragma.messaging.domain.model.StatusPin;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationDTOResponse {
    private Long idNotification;
    private String phoneNumber;
    private String message;
    private String pin;
    private Long idOrder;
    private StatusPin statusPin;
    private boolean sent;
}
