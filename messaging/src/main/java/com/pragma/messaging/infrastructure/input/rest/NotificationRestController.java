package com.pragma.messaging.infrastructure.input.rest;

import com.pragma.messaging.application.dto.NotificationDTORequest;
import com.pragma.messaging.application.dto.NotificationDTOResponse;
import com.pragma.messaging.application.handler.INotificationHandler;
import com.pragma.messaging.infrastructure.constant.ConstantGeneralMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/messaging")
@Slf4j
public class NotificationRestController {
    private final INotificationHandler iNotificationHandler;

    @PostMapping("/send-sms")
    public ResponseEntity<NotificationDTOResponse> sendSMS(
            @Valid @RequestBody NotificationDTORequest notificationDTORequest) {
        log.info("{},{}", ConstantGeneralMessage.SENDING_SMS_TO, notificationDTORequest.getPhoneNumber());
        return ResponseEntity.ok(iNotificationHandler.sendNotification(notificationDTORequest));
    }

    @GetMapping("/get-notification")
    public ResponseEntity<NotificationDTOResponse> getNotification(@RequestParam String number){
       return ResponseEntity.ok(iNotificationHandler.getNotificationByPhone(number));
    }
}
