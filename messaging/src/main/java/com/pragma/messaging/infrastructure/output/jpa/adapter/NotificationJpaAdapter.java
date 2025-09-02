package com.pragma.messaging.infrastructure.output.jpa.adapter;

import com.pragma.messaging.domain.model.Notification;
import com.pragma.messaging.domain.spi.INotificationPersistencePort;
import com.pragma.messaging.infrastructure.output.jpa.entity.NotificationEntity;
import com.pragma.messaging.infrastructure.output.jpa.mapper.INotificationEntityMapper;
import com.pragma.messaging.infrastructure.output.jpa.repository.INotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationJpaAdapter implements INotificationPersistencePort {
    private final INotificationRepository iNotificationRepository;
    private final INotificationEntityMapper iNotificationEntityMapper;

    @Override
    public Optional<Notification> findById(Long idNotification) {
        return iNotificationRepository.findById(idNotification)
                .map(iNotificationEntityMapper::toDomain);
    }

    @Override
    public Optional<Notification> findByPhoneNumberUser(String phoneNumberUser) {
        return iNotificationRepository.findNotificationByPhoneNumberUser(phoneNumberUser)
                .map(iNotificationEntityMapper::toDomain);
    }

    @Override
    public Notification save(Notification notification) {
        NotificationEntity notificationEntity =iNotificationEntityMapper.toEntity(notification);
        return iNotificationEntityMapper.toDomain(iNotificationRepository.save(notificationEntity));
    }

}

