package org.techtown.started.ui.home;

public class Schedule {
    public Schedule(String spot, String match_date) {
        Spot = spot;
        Match_date = match_date;
    }
    public Schedule(){}

    public String getSpot() {
        return Spot;
    }

    public void setSpot(String spot) {
        Spot = spot;
    }

    public String getMatch_date() {
        return Match_date;
    }

    public void setMatch_date(String match_date) {
        Match_date = match_date;
    }

    String Spot;
    String Match_date;
}
