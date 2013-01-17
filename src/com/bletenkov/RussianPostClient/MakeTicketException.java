package com.bletenkov.RussianPostClient;

public class MakeTicketException extends Exception {

    private String number;
    private String message;

    public MakeTicketException(String number, String message){
        this.number = number;
        this.message = message;
    }

    @Override
    public String toString(){
        return "[" + number + "]" + " " + message;
    }
}
