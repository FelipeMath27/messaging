package com.pragma.messaging.domain.usecase;

import com.pragma.messaging.domain.SMS.ITwilioPort;
import com.pragma.messaging.domain.api.INotificationServicePort;
import com.pragma.messaging.domain.model.Notification;
import com.pragma.messaging.domain.utils.ConstantsErrorMessages;
import com.pragma.messaging.domain.validator.PinGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class NotificationUseCase implements INotificationServicePort {
    private final ITwilioPort iTwilioPort;
    private boolean isSent = false;

    @Override
    public Notification sendNotification(Notification notification) {
        isSent = iTwilioPort.sendSMS(notification);
        notification.setSent(isSent);
        String pin = PinGenerator.generatePin(notification.getPhoneNumber());
        notification.setPin(pin);
        notification.setMessage(ConstantsErrorMessages.YOUR_ORDER_IS_READY + " your pin is:" + pin);
        return notification;
    }
}
