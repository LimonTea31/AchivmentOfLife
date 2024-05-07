package com.example.achivmentoflife.model;

public class Marks {
    private int idMarks;
    private int idHabbit;
    private int TotalMarks;
    private int MarksRow;

    public Marks(int idMarks, int idHabbit, int totalMarks, int marksRow) {
        this.idMarks = idMarks;
        this.idHabbit = idHabbit;
        TotalMarks = totalMarks;
        MarksRow = marksRow;
    }

    public Marks(int idHabbit, int totalMarks, int marksRow) {
        this.idHabbit = idHabbit;
        TotalMarks = totalMarks;
        MarksRow = marksRow;
    }

    public int getIdMarks() {
        return idMarks;
    }

    public void setIdMarks(int idMarks) {
        this.idMarks = idMarks;
    }

    public int getIdHabbit() {
        return idHabbit;
    }

    public void setIdHabbit(int idHabbit) {
        this.idHabbit = idHabbit;
    }

    public int getTotalMarks() {
        return TotalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        TotalMarks = totalMarks;
    }

    public int getMarksRow() {
        return MarksRow;
    }

    public void setMarksRow(int marksRow) {
        MarksRow = marksRow;
    }
}
