package com.pragma.messaging.domain.usecase;

import com.pragma.messaging.domain.SMS.ITwilioPort;
import com.pragma.messaging.domain.api.INotificationServicePort;
import com.pragma.messaging.domain.model.Notification;
import com.pragma.messaging.domain.model.StatusPin;
import com.pragma.messaging.domain.spi.INotificationPersistencePort;
import com.pragma.messaging.domain.utils.ConstantLocal;
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
               .map(exist -> {
                   if(exist.getStatusPin() == StatusPin.ACT){
                       exist.setStatusPin(StatusPin.INA);
                       iNotificationPersistencePort.save(exist);
                   }
                   return createdSendNotification(notification);
               })
               .orElseGet(()-> createdSendNotification(notification));
    }

    private Notification createdSendNotification(Notification notification) {
        String newPin = PinGenerator.generatePin(notification.getPhoneNumber());
        notification.setPin(newPin);
        notification.setMessage(ConstantsErrorMessages.YOUR_ORDER_IS_READY);
        notification.setDateNotification(LocalDateTime.now());
        notification.setStatusPin(StatusPin.ACT);
        notification.setSent(iTwilioPort.sendSMS(notification));
        return iNotificationPersistencePort.save(notification);
    }

    @Override
    public Notification getNotification(String phoneNumber) {
        Notification notification = iNotificationPersistencePort.findByPhoneNumberUser(phoneNumber)
                .orElseThrow(()->new RuntimeException(ConstantsErrorMessages.NOTIFICATION_NOT_FOUND));

        validateExpiration(notification);
        return notification;
    }

    private void validateExpiration(Notification notification){
        LocalDateTime expirationTime = notification.getDateNotification().plusMinutes(ConstantLocal.PIN_EXPIRATION_MINUTES);
        if(LocalDateTime.now().isAfter(expirationTime)){
            notification.setStatusPin(StatusPin.INA);
            iNotificationPersistencePort.save(notification);
            throw new RuntimeException(ConstantsErrorMessages.PIN_HAS_EXPIRED);
        }
    }





}
