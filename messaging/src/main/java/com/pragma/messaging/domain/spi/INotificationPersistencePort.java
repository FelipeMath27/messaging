package com.pragma.messaging.domain.spi;

import com.pragma.messaging.domain.model.Notification;
import org.aspectj.weaver.ast.Not;

import java.util.Optional;

public interface INotificationPersistencePort {
    Optional<Notification> findById(Long idNotification);

    Optional<Notification> findByPhoneNumberUser(String phoneNumber);
    Notification save(Notification notification);
}
