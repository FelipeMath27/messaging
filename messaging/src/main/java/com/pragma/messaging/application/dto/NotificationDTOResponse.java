package com.pragma.messaging.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationDTOResponse {
    private String phoneNumber;
    private String message;
    private String pin;
    private boolean sent;
}
