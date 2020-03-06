package org.techtown.started.ui.home;

public class Notice {
    private String Title;
    private String Contents;
    private String Date;
    private int Number;

    public Notice(String title, String contents, String date, int number) {
        Title = title;
        Contents = contents;
        Date = date;
        Number = number;
    }
    public Notice(){}
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        this.Contents = contents;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        this.Number = number;
    }




}
