package com.bletenkov.PostTrackerLite;

import android.text.format.DateFormat;
import java.sql.Date;

public class TrackNumberInfo {

    private int id;
    private String trackNumber;
    private long lastCheck;
    private String inCountry;
    private String outCountry;
    private String comment;
    private long dateAdd;

    public TrackNumberInfo(int id,
                           String number,
                           String inCountry,
                           String outCountry,
                           String comment,
                           long dateAdd,
                           long lastCheck){
        this.id = id;
        this.trackNumber = number;
        this.inCountry = inCountry;
        this.outCountry = outCountry;
        this.comment = comment;
        this.dateAdd = dateAdd;
        this.lastCheck = lastCheck;
    }

    //id
    public int getId(){
        return id;
    }

    //трэк номер
    public String getTrackNumber(){
        return trackNumber;
    }

    //коментарий
    public String getComment(){
        return comment;
    }

    //страна получения
    public String getInCountry(){
        return inCountry;
    }

    //страна отправки
    public String getOutCountry(){
        return outCountry;
    }

    //последняя проверка
    public String getLastCheck(){
        return (DateFormat.format("dd.MM.yyyy", new Date(lastCheck))).toString();
    }

    //разница дат между добавлением и текущим днем
    public String getDatesBetween(){
        long curdate = System.currentTimeMillis();
        long diff = curdate - dateAdd;

        return Long.toString(diff / (1000 * 3600 * 24));
    }

    //последнее состояние
    public void setLastCheck(long lastCheck){
        this.lastCheck = lastCheck;
    }

}
