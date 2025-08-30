package com.pragma.messaging.domain.SMS;

import com.pragma.messaging.domain.model.Notification;

public interface ITwilioPort {
    boolean sendSMS(Notification notification);
}
