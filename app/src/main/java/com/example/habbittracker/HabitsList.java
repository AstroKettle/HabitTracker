package com.example.habbittracker;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HabitsList extends Fragment {

    private RecyclerView recyclerView;
    List<DataClass> dataList;
    private HabitAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_habits_list, container, false);
    }
    FirebaseAuth mAuth;
    String title;
    String reg;
    Image img;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (getArguments() != null) {
//            String crKey = getArguments().getString("key");
//            int prog =
//            DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference("Habits");
//            mDataBase.child(crKey).child("dataProg").
//        }
        recyclerView = view.findViewById(R.id.habitList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false));
        mAuth = FirebaseAuth.getInstance();
        dataList = new ArrayList<>();
        adapter = new HabitAdapter(getContext(), dataList);
        recyclerView.setAdapter(adapter);
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    if(itemSnapshot.getValue().getClass() != String.class) {
                        DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                        dataClass.setKey(itemSnapshot.getKey());
                        try {
                            dataClass.setProgress(itemSnapshot.child("dataProg").getValue(Integer.class));
                        } catch (NullPointerException e) {
                            dataClass.setProgress(0);
                        }

                        dataList.add(dataClass);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                ;
            }
        });

    }
}