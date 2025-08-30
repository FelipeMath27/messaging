package com.pragma.messaging.application.dto;

import com.pragma.messaging.domain.utils.ConstantsErrorMessages;
import com.pragma.messaging.infrastructure.constant.ConstantGeneralMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationDTORequest {
    @NotBlank(message = ConstantsErrorMessages.CANT_BE_NULL)
    @Pattern(regexp = ConstantsErrorMessages.PHONE_REGEX, message = ConstantsErrorMessages.INVALID_PHONE_FORMAT)
    private String phoneNumber;
}