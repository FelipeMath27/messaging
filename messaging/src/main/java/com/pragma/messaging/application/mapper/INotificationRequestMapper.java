package com.pragma.messaging.application.mapper;

import com.pragma.messaging.application.dto.NotificationDTORequest;
import com.pragma.messaging.domain.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface INotificationRequestMapper {
    Notification toNotification(NotificationDTORequest notificationDTORequest);
}
