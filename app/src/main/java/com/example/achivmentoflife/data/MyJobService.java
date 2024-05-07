package com.example.achivmentoflife.data;

import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import androidx.core.app.NotificationCompat;

import com.example.achivmentoflife.R;
import com.example.achivmentoflife.model.Habbit;

import java.util.List;

public class MyJobService extends JobService {
    List<Habbit> habbitList;
    @Override
    public boolean onStartJob(JobParameters params) {
        new DatabaseTask().execute(params);
        return true;
    }
    private class DatabaseTask extends AsyncTask<JobParameters, Void, Void> {
        @Override
        protected Void doInBackground(JobParameters... params) {

            DatabaseHandler db = new DatabaseHandler(MyJobService.this);
            habbitList = db.getHabforUpdate("Day");
            int n = habbitList.size();

            for (int i = 0; i < n; i++) {
                Habbit habbit = habbitList.get(i);
                Habbit updatedHabit = new Habbit(habbit.getId(), habbit.getName(),
                        habbit.getDescription(), 0, habbit.getDateCreate(), habbit.getDayUpdate());
                db.updateHab(updatedHabit);
            }
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), "channel_id")
                    .setSmallIcon(R.drawable.baseline_task_alt_24)
                    .setContentTitle("Ваш заголовок")
                    .setContentText("Ваш текст уведомления")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            notificationManager.notify(1, builder.build());

            jobFinished(params[0], false); // Уведомляем систему о завершении работы
            return null;
        }
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}

