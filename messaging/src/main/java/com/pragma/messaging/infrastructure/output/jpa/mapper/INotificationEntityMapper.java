package com.pragma.messaging.infrastructure.output.jpa.mapper;

import com.pragma.messaging.domain.model.Notification;
import com.pragma.messaging.infrastructure.output.jpa.entity.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface INotificationEntityMapper {

    @Mapping(source = "phoneNumber", target = "phoneNumberUser")
    @Mapping(source = "pin", target = "pinOrder")
    @Mapping(source = "dateNotification", target = "dateNotification") // se resuelve con un method extra
    NotificationEntity toEntity(Notification notification);

    @Mapping(source = "phoneNumberUser", target = "phoneNumber")
    @Mapping(source = "pinOrder", target = "pin")
    @Mapping(source = "dateNotification", target = "dateNotification")
    Notification toDomain(NotificationEntity entity);
}
