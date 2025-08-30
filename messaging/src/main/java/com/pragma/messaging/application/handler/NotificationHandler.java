package com.pragma.messaging.application.handler;

import com.pragma.messaging.application.dto.NotificationDTORequest;
import com.pragma.messaging.application.dto.NotificationDTOResponse;
import com.pragma.messaging.application.mapper.INotificationRequestMapper;
import com.pragma.messaging.application.mapper.INotificationResponseMapper;
import com.pragma.messaging.domain.api.INotificationServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NotificationHandler implements INotificationHandler{
    private final INotificationRequestMapper iNotificationRequestMapper;
    private final INotificationResponseMapper iNotificationResponseMapper;
    private final INotificationServicePort iNotificationServicePort;


    @Override
    public NotificationDTOResponse sendNotification(NotificationDTORequest notificationDTORequest) {
        return iNotificationResponseMapper.toResponse(
                iNotificationServicePort.sendNotification(
                        iNotificationRequestMapper.toNotification(notificationDTORequest)
                )
        );
    }
}
