package com.example.achivmentoflife.model;

public class Alert {
    private int idAlert;
    private int idHabbit;
    private String TimeAlert;

    public Alert(int idAlert, int idHabbit, String timeAlert) {
        this.idAlert = idAlert;
        this.idHabbit = idHabbit;
        TimeAlert = timeAlert;
    }

    public Alert(int idHabbit, String timeAlert) {
        this.idHabbit = idHabbit;
        TimeAlert = timeAlert;
    }

    public int getIdAlert() {
        return idAlert;
    }

    public void setIdAlert(int idAlert) {
        this.idAlert = idAlert;
    }

    public int getIdHabbit() {
        return idHabbit;
    }

    public void setIdHabbit(int idHabbit) {
        this.idHabbit = idHabbit;
    }

    public String getTimeAlert() {
        return TimeAlert;
    }

    public void setTimeAlert(String timeAlert) {
        TimeAlert = timeAlert;
    }
}
