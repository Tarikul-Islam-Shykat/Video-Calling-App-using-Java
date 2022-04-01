package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityDashboardBinding;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class dashboard extends AppCompatActivity {

    ActivityDashboardBinding db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dashboard);
        db= ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(db.getRoot());

        URL serverUrl;

        try {
            serverUrl = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions
                    .Builder()
                    .setServerURL(serverUrl)
                    .setWelcomePageEnabled(false)
                    .build();

            JitsiMeet.setDefaultConferenceOptions(options);
        }
        catch (MalformedURLException e) {
            Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
        }


        db.joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JitsiMeetConferenceOptions  option = new JitsiMeetConferenceOptions
                        .Builder()
                        .setRoom(db.codeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(dashboard.this, option);
            }
        });

    }
}