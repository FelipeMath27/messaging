package com.pragma.messaging.domain.api;

import com.pragma.messaging.domain.model.Notification;

public interface INotificationServicePort {

    Notification sendNotification(Notification notification);

    Notification getNotification(String phoneNumber);
}
