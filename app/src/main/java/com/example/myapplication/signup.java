package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {

    ActivitySignupBinding sign_binding;
    String name, email, password;
    FirebaseAuth auth;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_signup);
        sign_binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(sign_binding.getRoot());
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        sign_binding.sLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sign_binding.sLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = sign_binding.sNameBox.getText().toString();
                email = sign_binding.sEmailBox.getText().toString();
                password = sign_binding.sPasswordBox.getText().toString();
                //Toast.makeText(getApplicationContext(), password, Toast.LENGTH_SHORT).show();

                // storing data
                User user = new User();
                user.setEmail(email); user.setName(name); user.setPassword(password);

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            database.collection("Users") // we already created a collection named Users
                                    .document()
                                    .set(user) // setting data in the firestrore document
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            // WHEN DATA IS SUCCESSFULLY STORED IN THE FIRESTORE
                                            startActivity(new Intent(getApplicationContext(), login.class));
                                        }
                                    });
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        sign_binding.sCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
            }
        });

        sign_binding.sTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Wait we are working on it", Toast.LENGTH_SHORT).show();
            }
        });
    }
}