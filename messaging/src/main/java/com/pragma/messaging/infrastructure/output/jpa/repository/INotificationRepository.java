package com.pragma.messaging.infrastructure.output.jpa.repository;

import com.pragma.messaging.infrastructure.output.jpa.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INotificationRepository extends JpaRepository<NotificationEntity,Long> {
    Optional<NotificationEntity> findNotificationByPhoneNumberUser(String phoneNumberUser);
}
