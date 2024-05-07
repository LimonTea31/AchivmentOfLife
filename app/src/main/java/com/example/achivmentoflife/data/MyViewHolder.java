package com.example.achivmentoflife.data;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.achivmentoflife.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public Button buttonHabbit;
    public CheckBox checkBoxHab;
    public TextView habitDescriptionTextView;
    public Button button;

    public MyViewHolder(View itemView) {
        super(itemView);
        buttonHabbit = itemView.findViewById(R.id.btnHabbit);
        checkBoxHab = itemView.findViewById(R.id.cbDone);
//        button = itemView.findViewById(R.id.button);
    }
}
