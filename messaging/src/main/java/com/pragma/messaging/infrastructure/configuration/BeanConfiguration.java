package com.pragma.messaging.infrastructure.configuration;


import com.pragma.messaging.domain.SMS.ITwilioPort;
import com.pragma.messaging.domain.api.INotificationServicePort;
import com.pragma.messaging.domain.usecase.NotificationUseCase;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@RequiredArgsConstructor
@Configuration
public class BeanConfiguration {

    @PostConstruct
    public void initEnv() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("TWILIO_ACCOUNT_SID", Objects.requireNonNull(dotenv.get("TWILIO_ACCOUNT_SID")));
        System.setProperty("TWILIO_AUTH_TOKEN", Objects.requireNonNull(dotenv.get("TWILIO_AUTH_TOKEN")));
        System.setProperty("TWILIO_PHONE_NUMBER", Objects.requireNonNull(dotenv.get("TWILIO_PHONE_NUMBER")));
    }

    @Bean
    public INotificationServicePort iNotificationServicePort (ITwilioPort iTwilioPort){
        return new NotificationUseCase(iTwilioPort);
    }
}
