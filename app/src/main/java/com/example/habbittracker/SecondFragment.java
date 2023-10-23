package com.example.habbittracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.habbittracker.databinding.FragmentSecondBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.habbittracker.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondFragment extends Fragment {

    public FragmentSecondBinding binding;

    public Button RegBtn;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public TextInputEditText loginText, PassText;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        PassText = view.findViewById(R.id.UserPassword);
        loginText = view.findViewById(R.id.UserName);
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference users = db.getReference("Users");
        RegBtn = view.findViewById(R.id.LoginBtn);
        mAuth = FirebaseAuth.getInstance();
        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Login, Pass;
                Login = String.valueOf(loginText.getText());
                Pass = String.valueOf(PassText.getText());
                mAuth.createUserWithEmailAndPassword(Login, Pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    User user = new User();
                                    user.setEmail(Login);
                                    user.setPass(Pass);
                                    Statistic stat = new Statistic();
                                    users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                                    users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("stat").setValue(stat);

                                    Toast.makeText(getContext(), "Аккаунт создан успешно!",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(getContext(), "Ошибка аутентификации!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}