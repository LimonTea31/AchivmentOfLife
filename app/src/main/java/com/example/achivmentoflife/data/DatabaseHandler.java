package com.example.achivmentoflife.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.achivmentoflife.model.Habbit;
import com.example.achivmentoflife.utils.Util;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_HABBIT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ( " +
                Util.KEY_ID + " INTEGER PRIMARY KEY, " + Util.KEY_NAME + " TEXT, " +
                Util.KEY_DESCRIPTION + " TEXT, " + Util.KEY_DONE + " INTEGER, " + Util.KEY_DATE +
                " TEXT, " + Util.KEY_UPDATE + " TEXT "+" )";
        sqLiteDatabase.execSQL(CREATE_HABBIT_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);


    }
    public void AddHab(Habbit habbit)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       contentValues.put(Util.KEY_NAME, habbit.getName());
       contentValues.put(Util.KEY_DESCRIPTION, habbit.getDescription());
       contentValues.put(Util.KEY_DONE, habbit.getDone());
       contentValues.put(Util.KEY_DATE, habbit.getDateCreate());
       contentValues.put(Util.KEY_UPDATE, habbit.getDayUpdate());

       db.insert(Util.TABLE_NAME, null,contentValues);
       db.close();
    }
    public Habbit getHab(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.KEY_NAME,
                Util.KEY_DESCRIPTION, Util.KEY_DONE, Util.KEY_DATE, Util.KEY_UPDATE},
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        Habbit habbit = new Habbit(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)),
                cursor.getString(4),cursor.getString(5));
        return habbit;
    }

    public Habbit getHab(String nameHab, String descriptionHab) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectHab = "SELECT * FROM " + Util.TABLE_NAME + " WHERE "
                + Util.KEY_NAME + " = ? AND " + Util.KEY_DESCRIPTION + " = ?";
        Cursor cursor = db.rawQuery(selectHab, new String[]{nameHab, descriptionHab});
        Habbit habit = null;
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(Util.KEY_ID);
            int nameIndex = cursor.getColumnIndex(Util.KEY_NAME);
            int descriptionIndex = cursor.getColumnIndex(Util.KEY_DESCRIPTION);
            int doneIndex = cursor.getColumnIndex(Util.KEY_DONE);
            int dateIndex = cursor.getColumnIndex(Util.KEY_DATE);
            int dateUpdate = cursor.getColumnIndex(Util.KEY_UPDATE);

            if (idIndex != -1 && nameIndex != -1 && descriptionIndex != -1 && doneIndex != -1 && dateIndex != -1) {
                habit = new Habbit(Integer.parseInt(cursor.getString(idIndex)),
                        cursor.getString(nameIndex),
                        cursor.getString(descriptionIndex),
                        Integer.parseInt(cursor.getString(doneIndex)),
                        cursor.getString(dateIndex), cursor.getString(dateUpdate));
            }
            cursor.close();
        }
        return habit;
    }
    public List<Habbit> getHabforUpdate(String updateHab)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Habbit> habbitList = new ArrayList<>();
        String selectHabUpdate = "SELECT * FROM " + Util.TABLE_NAME + " WHERE " + Util.KEY_UPDATE + " = ?";
        Cursor cursor = db.rawQuery(selectHabUpdate, null);
        if (cursor.moveToFirst())
        {
            do {
                Habbit habbit = new Habbit();
                habbit.setId(Integer.parseInt(cursor.getString(0)));
                habbit.setName(cursor.getString(1));
                habbit.setDescription(cursor.getString(2));
                habbit.setDone(Integer.parseInt(cursor.getString(3)));
                habbit.setDateCreate(cursor.getString(4));
                habbit.setDayUpdate(cursor.getString(5));
                habbitList.add(habbit);
            }while (cursor.moveToNext());
        }
        return habbitList;
    }


    public List<Habbit> getAllHab()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Habbit> habbitList = new ArrayList<>();
        String selectAllHab = "Select * from " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllHab, null);
        if (cursor.moveToFirst())
        {
            do {
                Habbit habbit = new Habbit();
                habbit.setId(Integer.parseInt(cursor.getString(0)));
                habbit.setName(cursor.getString(1));
                habbit.setDescription(cursor.getString(2));
                habbit.setDone(Integer.parseInt(cursor.getString(3)));
                habbit.setDateCreate(cursor.getString(4));
                habbit.setDayUpdate(cursor.getString(5));
                habbitList.add(habbit);
            }while (cursor.moveToNext());
        }
        return habbitList;
    }

    public int updateHab(Habbit habbit)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, habbit.getName());
        contentValues.put(Util.KEY_DESCRIPTION, habbit.getDescription());
        contentValues.put(Util.KEY_DONE, habbit.getDone());
        contentValues.put(Util.KEY_DATE, habbit.getDateCreate());
        contentValues.put(Util.KEY_UPDATE, habbit.getDayUpdate());
        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String[]{String.valueOf(habbit.getId())});
    }
    public void deleteHab(Habbit habbit)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[]{String.valueOf(habbit.getId())});
        db.close();
    }
}
