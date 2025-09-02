package com.pragma.messaging.application.handler;

import com.pragma.messaging.application.dto.NotificationDTORequest;
import com.pragma.messaging.application.dto.NotificationDTOResponse;

public interface INotificationHandler {
    NotificationDTOResponse sendNotification(NotificationDTORequest notificationDTORequest);

    NotificationDTOResponse getNotificationByPhone(String phoneNumber);
}
