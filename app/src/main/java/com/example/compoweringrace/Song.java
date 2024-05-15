package com.example.compoweringrace;

public class Song {
    private String number;
    private String title;
    private String detail;

    public Song(String number, String title, String songDetail) {
        this.number = number;
        this.title = title;
        this.detail = songDetail;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}
