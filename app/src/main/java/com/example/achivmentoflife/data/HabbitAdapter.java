package com.example.achivmentoflife.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.achivmentoflife.R;
import com.example.achivmentoflife.model.Habbit;

import java.util.List;

public class HabbitAdapter extends RecyclerView.Adapter<HabbitAdapter.HabbitViewHolder> {

    private List<Habbit> habbitList;
    private Context context;


    public HabbitAdapter(List<Habbit> habbitList, Context context) {
        this.habbitList = habbitList;
        this.context =context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }


    @NonNull
    @Override
    public HabbitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habbit_maket, parent, false);
        return new HabbitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabbitViewHolder holder, int position) {
        Habbit habbit = habbitList.get(position);
        holder.bind(habbit);
    }

    @Override
    public int getItemCount() {
        return habbitList.size();
    }

    public class HabbitViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView timeElapsedTextView;
        private TextView createdTimeStampTextView;
        private CheckBox checkBox;
        private Button button;
        private Context context;


        public HabbitViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btnHabbit);
            checkBox = itemView.findViewById(R.id.cbDone);

        }

        public void bind(Habbit habbit) {
            int numericValue = 1;
            boolean booleanValue = habbit.getDone() == 1;
            button.setText(habbit.getName());
            checkBox.setChecked(booleanValue);
        }



    }
}

