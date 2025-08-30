package com.pragma.messaging.domain.usecase;

import com.pragma.messaging.domain.SMS.ITwilioPort;
import com.pragma.messaging.domain.api.INotificationServicePort;
import com.pragma.messaging.domain.model.Notification;
import com.pragma.messaging.domain.utils.ConstantsErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class NotificationUseCase implements INotificationServicePort {
    private final ITwilioPort iTwilioPort;
    private boolean isSent = false;
    @Override
    public Notification sendNotification(Notification notification) {
        try {
            isSent = iTwilioPort.sendSMS(notification);
            log.info("{}", ConstantsErrorMessages.SMS_SENT_SUCCESSFULLY);
        } catch (Exception e) {
            log.error("{}, {}", ConstantsErrorMessages.ERROR_TO_SEND_SMS, e.getMessage());
        }
        notification.setSent(isSent);
        return notification;
    }
}
