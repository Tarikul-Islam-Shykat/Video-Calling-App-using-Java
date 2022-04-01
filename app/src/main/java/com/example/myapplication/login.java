package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    ActivityLoginBinding log;
    FirebaseAuth firebaseAuth;
    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        log = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(log.getRoot());
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();

        log.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), signup.class));
                finish();
            }
        });

        log.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = log.emailBox.getText().toString();
                password = log.passwordBox.getText().toString();

                firebaseAuth.
                        signInWithEmailAndPassword(email, password).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),dashboard.class));
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Log In error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}