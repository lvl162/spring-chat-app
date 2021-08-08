package com.team4.Transpeur.Model.DTO;

import java.util.Date;

public class SearchDTO {
    private String word;
    private String fromPlace;
    private String toPlace;
    private String transport;

    private Date fromTime;
    private Date toTime;

    public SearchDTO(String word, String fromPlace, String toPlace, String transport, Date fromTime, Date toTime) {
        this.word = word;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.transport = transport;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }
}
