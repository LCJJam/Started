package org.techtown.started.ui.home;

public class Notice {
    public Notice(int number, String title, String contents, String date) {
        Number = number;
        Title = title;
        Contents = contents;
        Date = date;
    }

    int Number;
    String Title;
    String Contents;
    String Date;
    // long now = System.currentMills();
    // Date date = new Date(now);
    // SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    // String getTime = simpleDate.format(mDate);

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}
