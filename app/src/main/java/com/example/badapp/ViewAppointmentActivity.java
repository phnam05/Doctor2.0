package com.example.badapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class ViewAppointmentActivity extends AppCompatActivity {
    ProgressBar progressBar;

    Button backHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);
        backHomeButton = findViewById(R.id.btnHome);
        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAppointmentActivity.this, PatientHomeActivity.class));
            }
        });
    }
}