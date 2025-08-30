package com.pragma.messaging.domain.validator;

public class PinGenerator {
    public static String generatePin(String phoneNumber) {
        String lastFourDigits = phoneNumber.substring(phoneNumber.length() - 4);

        int randomDigits = (int) (Math.random() * 9000) + 1000;

        return String.valueOf(randomDigits) + lastFourDigits;
    }
}
