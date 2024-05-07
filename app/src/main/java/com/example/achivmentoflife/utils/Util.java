package com.example.achivmentoflife.utils;

public class Util {
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "HabitTrackerDB";
    //Для Привычек
    public static final String TABLE_NAME = "Habbit";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_DESCRIPTION = "Description";
    public static final String KEY_DONE = "Done";
    public static final String KEY_DATE = "DateCreate";
    public static final String KEY_UPDATE = "TimeUpdate";
    //Для Оповещений
    public static final String ALERT_TABLE = "Alert";
    public static final String ALERT_ID = "idAlert";
    //public static final String HAB_ID = "id"; Лучше ссылаться на id из таблицы выше
    public static final String KEY_TIME = "TimeAlert";

    //Для отметок
    public static final String MARKS_TABLE = "Marks";
    public static final String MARKS_ID = "idMarks";
    //public static final String HAB_ID = "id"; Лучше ссылаться на id из таблицы выше
    public static final String KEY_TOTALMARKS = "TotalMarks";
    public static final String KEY_ROWMARKS = "RowMarks";
}
