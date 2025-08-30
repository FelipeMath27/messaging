    package com.pragma.messaging.infrastructure.output.twilio;

    import com.pragma.messaging.domain.SMS.ITwilioPort;
    import com.pragma.messaging.domain.model.Notification;
    import com.pragma.messaging.infrastructure.constant.ConstantGeneralMessage;
    import com.pragma.messaging.infrastructure.exceptions.InfrastructureException;
    import com.twilio.Twilio;
    import com.twilio.rest.api.v2010.account.Message;
    import com.twilio.type.PhoneNumber;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;

    @Component
    @RequiredArgsConstructor
    @Slf4j
    public class TwilioSMSAdapter implements ITwilioPort {

        @Value("${twilio.sid}")
        private String accountSid;

        @Value("${twilio.token}")
        private String authToken;

        @Value("${twilio.phone-number}")
        private String fromPhoneNumber;

        @Override
        public boolean sendSMS(Notification notification) {
            try {
                Twilio.init(accountSid,authToken);

                Message.creator(
                        new PhoneNumber(notification.getPhoneNumber()),
                        new PhoneNumber(fromPhoneNumber),
                        notification.getMessage()
                ).create();

                return true;
            } catch (Exception e) {
                log.error("{},{}",e.getMessage(), ConstantGeneralMessage.ERROR_SENDING_SMS);
                throw new InfrastructureException(e.getMessage());
            }
        }
    }
