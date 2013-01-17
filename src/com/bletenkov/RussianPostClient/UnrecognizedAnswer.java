package com.bletenkov.RussianPostClient;

public class UnrecognizedAnswer extends MakeTicketException {

    public UnrecognizedAnswer(String number, String message) {
        super(number, message);
    }
}
