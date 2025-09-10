package com.pragma.messaging.domain.utils;


public class ConstantsErrorMessages {

    public static final String INVALID_PHONE_FORMAT = "The phone number must have a maximum of 13 characters and may include '+'.";

    /**General constants*/
    public static final String CANT_BE_NULL = "This parameter can't be null";
    public static final String MESSAGE_CANT_BE_NULL = "Message can't be null";
    public static final String PIN_ITS_REQUIRED = "PIN is required";

    /**Regex*/
    public static final String PHONE_REGEX = "^\\+57\\d{10}$";
    public static final String PIN_REGEX = "^[0-9]{4,6}$";
    public static final String INVALID_PIN_FORMAT = "The PIN must be numeric and contain between 4 to 6 digits.";

    public static final String ERROR_TO_SEND_SMS = "There was an error sending the SMS";
    public static final String SMS_SENT_SUCCESSFULLY = "SMS sent successfully";
    public static final String YOUR_ORDER_IS_READY = "Your order is ready. Use the following PIN to collect it: ";
    public static final String ID_REQUIRED = "ID is required";
    public static final String ID_GREATER_THAN_ZERO = "ID must be greater than zero";
    public static final String NOTIFICATION_NOT_FOUND = "Notification not found";
    public static final String PIN_HAS_EXPIRED = "PIN HAS EXPIRED";

    private ConstantsErrorMessages() {
    }
}
