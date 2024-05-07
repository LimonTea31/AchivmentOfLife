package com.example.achivmentoflife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.achivmentoflife.data.DatabaseHandler;
import com.example.achivmentoflife.data.MyJobService;
import com.example.achivmentoflife.model.Habbit;
import com.example.achivmentoflife.ui.home.HomeFragment;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateHabitActivity extends AppCompatActivity {

   TextView ptNameHab;
    TextView ptDescriptHab;
    Button btnSave, btnRedact, btnDell;
    Button btnDay, btnWeek, btnMonth, btnTime;
    ImageView ImgBack;
    boolean redact = false;
    RemoteViews remoteViews;
    String Update = "Day";
    String habitName;
    DatabaseHandler db;
    SimpleDateFormat sdf = new SimpleDateFormat("HH.mm", Locale.getDefault());
    String habitDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);
        try{
            Intent intents = new Intent(getBaseContext(), AlarmReceiver.class);
            PendingIntent.getBroadcast(getBaseContext(), 1, intents, PendingIntent.FLAG_IMMUTABLE);
            //Потом исправлю, нужно для теста
            ImgBack = findViewById(R.id.imgClick);
            ptNameHab = findViewById(R.id.ptNameHabbit);
            ptDescriptHab = findViewById(R.id.ptDescript);
            btnSave = findViewById(R.id.btnSave);
            btnRedact = findViewById(R.id.btnRedact);
            btnDell = findViewById(R.id.btnDell);
            btnDay = findViewById(R.id.btnDay);
            btnWeek = findViewById(R.id.btnWeek);
            btnMonth = findViewById(R.id.btnMonth);
            btnTime = findViewById(R.id.btnTime);
            Intent intent = getIntent();
            if (intent.hasExtra("habitName") && intent.hasExtra("habitDescription")) {
                habitName = getIntent().getStringExtra("habitName");
                habitDescription = getIntent().getStringExtra("habitDescription");
                ptNameHab.setText(habitName);
                ptDescriptHab.setText(habitDescription);
                btnSave.setVisibility(View.INVISIBLE);
                btnRedact.setVisibility(View.VISIBLE);
                btnDell.setVisibility(View.VISIBLE);
            }
            ImgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            btnTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                            .setTimeFormat(TimeFormat.CLOCK_24H)
                            .setHour(12)
                            .setMinute(0)
                            .setTitleText("Выберите время для оповещения")
                            .build();
                    materialTimePicker.addOnPositiveButtonClickListener(view -> {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
                        calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());

                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        PendingIntent alarmInfoPendingIntent = getAlarmInfoPendingIntent(getBaseContext());
                        PendingIntent alarmActionPendingIntent = getAlarmActionPendingIntent(getBaseContext());

                        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), alarmInfoPendingIntent);
                        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), alarmActionPendingIntent);

                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        Toast.makeText(CreateHabitActivity.this, "Уведомление установлено на " + sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
                    });

                    materialTimePicker.show(getSupportFragmentManager(), "tag_picker");
                }
            });


            btnDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CreateHabitActivity.this, "Привычка будет обновляться раз в день", Toast.LENGTH_SHORT).show();
                    int color = ContextCompat.getColor(getBaseContext(), R.color.selectedBlue);
                    int UnSelColor = ContextCompat.getColor(getBaseContext(), R.color.white);
                    btnDay.setBackgroundTintList(ColorStateList.valueOf(color));
                    btnWeek.setBackgroundTintList(ColorStateList.valueOf(UnSelColor));
                    btnMonth.setBackgroundTintList(ColorStateList.valueOf(UnSelColor));
                    Update = "Day";



                }
            });
            btnWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CreateHabitActivity.this, "Привычка будет обновляться в конце недели", Toast.LENGTH_SHORT).show();
                    int color = ContextCompat.getColor(getBaseContext(), R.color.selectedBlue);
                    int UnSelColor = ContextCompat.getColor(getBaseContext(), R.color.white);
                    btnDay.setBackgroundTintList(ColorStateList.valueOf(UnSelColor));
                    btnWeek.setBackgroundTintList(ColorStateList.valueOf(color));
                    btnMonth.setBackgroundTintList(ColorStateList.valueOf(UnSelColor));
                    Update = "Week";

                }
            });
            btnMonth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CreateHabitActivity.this,
                            "Привычка будет обновляться в конце месяца", Toast.LENGTH_SHORT).show();
                    int color = ContextCompat.getColor(getBaseContext(), R.color.selectedBlue);
                    int UnSelColor = ContextCompat.getColor(getBaseContext(), R.color.white);
                    btnDay.setBackgroundTintList(ColorStateList.valueOf(UnSelColor));
                    btnWeek.setBackgroundTintList(ColorStateList.valueOf(UnSelColor));
                    btnMonth.setBackgroundTintList(ColorStateList.valueOf(color));

                    Update = "Month";

                }
            });


            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentDateText = getCurrentDateAsString("dd.MM.yyyy");
                    String habitName = ptNameHab.getText().toString();
                    String habitDescription = ptDescriptHab.getText().toString();
                    Update = "Day";
                    db = new DatabaseHandler(CreateHabitActivity.this);
                    db.AddHab(new Habbit(habitName, habitDescription, 0, currentDateText, Update));
                    finish();
                }
            });
            btnRedact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String habitName = getIntent().getStringExtra("habitName");
                    String habitDescription = getIntent().getStringExtra("habitDescription");
                    db = new DatabaseHandler(CreateHabitActivity.this);
                    Habbit habit = db.getHab(habitName, habitDescription);
                    if (habit != null) {
                        Habbit updatedHabit = new Habbit(habit.getId(), ptNameHab.getText().toString(), ptDescriptHab.getText().toString(), habit.getDone(), habit.getDateCreate(), habit.getDayUpdate());
                        db.updateHab(updatedHabit);
                        Toast.makeText(CreateHabitActivity.this, "Привычка успешно обновлена", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CreateHabitActivity.this, "Привычка не найдена", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            btnDell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db = new DatabaseHandler(CreateHabitActivity.this);
                    Habbit delHab = db.getHab(habitName, habitDescription);
                    if (delHab != null) {
                        db.deleteHab(delHab);
                        Toast.makeText(CreateHabitActivity.this, "Привычка успешно удалена", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CreateHabitActivity.this, "Привычка не найдена", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }catch (Exception e){
            Toast.makeText(CreateHabitActivity.this, "Проблема в создании", Toast.LENGTH_SHORT).show();


        }

    }

    private PendingIntent getAlarmInfoPendingIntent(Context context){
        Intent alarmInfoIntent = new Intent(context, AlarmReceiver.class);
        alarmInfoIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getBroadcast(context, 0, alarmInfoIntent, PendingIntent.FLAG_IMMUTABLE);
    }

    private PendingIntent getAlarmActionPendingIntent(Context context){
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_IMMUTABLE);
    }





    public static String getCurrentDateAsString(String format) {
        Date currentDate = new Date(); // Получаем текущую дату и время
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(currentDate);
    }
}