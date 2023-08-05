package com.example.habbittracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    public Button LogBtn, prevBtn;
    public TextInputEditText loginText, PassText;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogBtn = view.findViewById(R.id.LoginBtn);
        prevBtn = view.findViewById(R.id.second_button);
        loginText = view.findViewById(R.id.UserNameLogin);
        PassText = view.findViewById(R.id.UserPasswordLogin);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        LogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signInWithEmailAndPassword(loginText.getText().toString(), PassText.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_FirstFragment);
                            }
                        });
            }
        });

    }
}