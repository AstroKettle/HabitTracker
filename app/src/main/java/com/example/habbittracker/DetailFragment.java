package com.example.habbittracker;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.EventListener;


public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
    TextView detailTitle, detailProg;
    CheckBox completeDay;
    CalendarView cl;
    Button detBtn;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    Statistic statSnap;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailTitle = view.findViewById(R.id.detailTitle);
        detailProg = view.findViewById(R.id.progressValue);

        String title = getArguments().getString("Title");
        String crKey = getArguments().getString("Key");

        int reg = getArguments().getInt("Reg");
        int prog = getArguments().getInt("prog");

        detailTitle.setText(title);
        detailProg.setText(String.valueOf(prog));

        completeDay = view.findViewById(R.id.completeCheckBox);
        cl = view.findViewById(R.id.calendarView);
        detBtn = view.findViewById(R.id.saveDetail);

        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Users")
                .child(user.getUid()).child("stat");
        ValueEventListener eventListener;
        eventListener = dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                statSnap = snapshot.getValue(Statistic.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                ;
            }
        });
        Bundle bundle2 = new Bundle();
        detBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (completeDay.isChecked()) {
                    int complete = getArguments().getInt("compl");
                    int localDate = LocalDate.now().getDayOfMonth();
                    if (complete != localDate) {
                        int progress = prog;
                        progress++;
                        statSnap.setDaysInTracker(statSnap.getDaysInTracker() + 1);
                        if (progress == reg) {
                            statSnap.setHabitsComplete(statSnap.getHabitsComplete() + 1);
                        }
                        if (complete - localDate == 1) {
                            statSnap.setCurrentStrike(statSnap.getCurrentStrike() + 1);
                            if (statSnap.getCurrentStrike() > statSnap.getMaxStrike()) {
                                statSnap.setMaxStrike(statSnap.getCurrentStrike());
                            }
                        } else {
                            statSnap.setCurrentStrike(0);
                        }

                        DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference();
                        mDataBase.child("Users").child(user.getUid())
                                .child(crKey).child("dataProg").setValue(progress);
                        mDataBase.child("Users").child(user.getUid())
                                .child(crKey).child("dailyComplete").setValue(localDate);
                        mDataBase.child("Users").child(user.getUid())
                                .child("stat").setValue(statSnap);

                        bundle2.putInt("progr", progress);
                        bundle2.putString("key", crKey);
                        bundle2.putInt("day", localDate);
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Сегодня Вы уже выполнили план!",
                                Toast.LENGTH_SHORT).show();

                    }

                }
                Navigation.findNavController(view)
                        .navigate(R.id.action_detailFragment_to_habitsList, bundle2);
            }
        });

    }
}