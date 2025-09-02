package com.pragma.messaging.infrastructure.output.jpa.entity;

import com.pragma.messaging.domain.model.StatusPin;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name = "PRG_TBL_NOTIFICATION_SMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotification", nullable = false)
    Long idNotification;

    @NotNull(message = "The date of the notification cannot be null")
    @Column(name = "dateNotification", nullable = false)
    private LocalDateTime dateNotification;

    @NotBlank(message = "The number cannot be null")
    @Column(name = "phoneNumberUser", nullable = false)
    private String phoneNumberUser;

    @Column(name = "idOrder", nullable = false)
    private Long idOrder;

    @NotBlank(message = "The pin cannot be null")
    @Column(name = "pinOrder", nullable = false)
    private String pinOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusPin", nullable = false)
    private StatusPin statusPin;

}
