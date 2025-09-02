package com.pragma.messaging.domain.usecase;

import com.pragma.messaging.domain.SMS.ITwilioPort;
import com.pragma.messaging.domain.api.INotificationServicePort;
import com.pragma.messaging.domain.model.Notification;
import com.pragma.messaging.domain.model.StatusPin;
import com.pragma.messaging.domain.spi.INotificationPersistencePort;
import com.pragma.messaging.domain.utils.ConstantsErrorMessages;
import com.pragma.messaging.domain.validator.PinGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
public class NotificationUseCase implements INotificationServicePort {
    private final INotificationPersistencePort iNotificationPersistencePort;
    private final ITwilioPort iTwilioPort;
    private boolean isSent = false;

    @Override
    public Notification sendNotification(Notification notification) {
        return iNotificationPersistencePort.findByPhoneNumberUser(notification.getPhoneNumber())
                .map(existing -> {
                    if (existing.getStatusPin() == StatusPin.ACT) {
                        existing.setStatusPin(StatusPin.INA);
                        iNotificationPersistencePort.save(existing);
                    }

                    String newPin = PinGenerator.generatePin(notification.getPhoneNumber());
                    notification.setPin(newPin);
                    notification.setMessage(ConstantsErrorMessages.YOUR_ORDER_IS_READY + " " + newPin);
                    notification.setDateNotification(LocalDateTime.now());
                    notification.setStatusPin(StatusPin.ACT);
                    notification.setSent(iTwilioPort.sendSMS(notification));

                    return iNotificationPersistencePort.save(notification);
                })
                .orElseGet(() -> {

                    String newPin = PinGenerator.generatePin(notification.getPhoneNumber());
                    notification.setPin(newPin);
                    notification.setMessage(ConstantsErrorMessages.YOUR_ORDER_IS_READY + " " + newPin);
                    notification.setDateNotification(LocalDateTime.now());
                    notification.setStatusPin(StatusPin.ACT);
                    notification.setSent(iTwilioPort.sendSMS(notification));

                    return iNotificationPersistencePort.save(notification);
                });
    }

    @Override
    public Notification getNotification(String phoneNumber) {
        return iNotificationPersistencePort.findByPhoneNumberUser(phoneNumber)
                .orElseThrow(()->new RuntimeException(ConstantsErrorMessages.NOTIFICATION_NOT_FOUND));
    }
}
