package com.example.habbittracker;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.habbittracker.databinding.FragmentFirstBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    TextView detailText, detailCompl, detailStrike;
    LinearLayout layout;
    CardView statCard;
    Statistic statSnap;
    DataClass statSnap2;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    FloatingActionButton NewHabBtn;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        NewHabBtn = view.findViewById(R.id.floatingActionButton);
        NewHabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_habitCreator);
            }
        });
        detailText = view.findViewById(R.id.details_days);
        detailCompl = view.findViewById(R.id.details_compl);
        detailStrike = view.findViewById(R.id.details_strike);
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Users")
                .child(user.getUid()).child("stat");
        ValueEventListener eventListener;

        eventListener = dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                statSnap = snapshot.getValue(Statistic.class);
                if (statSnap != null) {
                    detailText.setText(detailText.getText().toString() + statSnap.getDaysInTracker());
                    detailCompl.setText(detailCompl.getText().toString() + statSnap.getHabitsComplete());
                    detailStrike.setText(detailStrike.getText().toString() + statSnap.getMaxStrike());
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                ;
            }
        });
        layout = view.findViewById(R.id.layout);
        statCard = view.findViewById(R.id.stat);

        statCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (detailText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
                int s = (detailCompl.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
                int t = (detailStrike.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
                TransitionManager.beginDelayedTransition(layout, new AutoTransition());
                detailText.setVisibility(v);
                detailCompl.setVisibility(s);
                detailStrike.setVisibility(t);
            }
        });
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }

}