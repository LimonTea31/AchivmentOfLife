package com.example.achivmentoflife.model;

import java.util.Date;

public class Habbit {
    private int id;
    private String Name;
    private String Description;
    private int Done;
    private String DateCreate;
    private String DayUpdate;
    public Habbit() {
    }
    public Habbit(int id, String name, String description, int done, String date, String update) {
        this.id = id;
        Name = name;
        Description = description;
        Done = done;
        DateCreate = date;
        DayUpdate = update;

    }
    public Habbit(String name, String description, int done, String date, String update) {
        Name = name;
        Description = description;
        Done = done;
        DateCreate = date;
        DayUpdate = update;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public int getDone() {
        return Done;
    }
    public void setDone(int done) {
        Done = done;
    }
    public String getDateCreate() {
        return DateCreate;
    }
    public void setDateCreate(String dateCreate) {
        DateCreate = dateCreate;
    }

    public String getDayUpdate() {
        return DayUpdate;
    }

    public void setDayUpdate(String dayUpdate) {
        DayUpdate = dayUpdate;
    }
}
