package com.example.achivmentoflife.ui.home;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.achivmentoflife.CreateHabitActivity;
import com.example.achivmentoflife.R;
import com.example.achivmentoflife.RecyclerItemClickListener;
import com.example.achivmentoflife.data.DatabaseHandler;
import com.example.achivmentoflife.data.HabbitAdapter;
import com.example.achivmentoflife.data.MyJobService;
import com.example.achivmentoflife.data.MyViewHolder;
import com.example.achivmentoflife.databinding.FragmentHomeBinding;
import com.example.achivmentoflife.model.Habbit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    Button buttonHab;
    HabbitAdapter adapter;
    List<Habbit> habbitList;
    DatabaseHandler db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = new DatabaseHandler(requireContext());
        habbitList = db.getAllHab();
        adapter = new HabbitAdapter(habbitList, requireContext());
        recyclerView = root.findViewById(R.id.rv_habits);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(requireContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        CheckBox cbDone = view.findViewById(R.id.cbDone);
                        int check;
                        if (cbDone.isChecked()) {
                            cbDone.setChecked(false);
                            check = 0;
                        } else {
                            cbDone.setChecked(true);
                            check = 1;
                        }

                        Habbit clickedHabbit = habbitList.get(position);
                        Habbit updatedHabit = new Habbit(clickedHabbit.getId(), clickedHabbit.getName(), clickedHabbit.getDescription(), check, clickedHabbit.getDateCreate(), clickedHabbit.getDayUpdate());
                        db.updateHab(updatedHabit);
                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        Habbit clickedHabbit = habbitList.get(position);
                        String habitName = clickedHabbit.getName();
                        String habitDescription = clickedHabbit.getDescription();

                        Intent intent = new Intent(requireContext(), CreateHabitActivity.class);
                        intent.putExtra("habitName", habitName);
                        intent.putExtra("habitDescription", habitDescription);
                        intent.putExtra("redact", true);
                        startActivity(intent);
                    }
                })
        );

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        db = new DatabaseHandler(requireContext());
        habbitList = db.getAllHab();
        adapter = new HabbitAdapter(habbitList, requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}