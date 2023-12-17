package com.example.badapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PatientHomeActivity extends AppCompatActivity {
    ProgressBar progressBar;

    TextView backPageText;

    Button logoutButton, setAppointmentButton, viewAppointmentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_patient);
        viewAppointmentButton = findViewById(R.id.btnViewAppointment);
        setAppointmentButton = findViewById(R.id.btnSetAppointment);
        logoutButton = findViewById(R.id.btnLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientHomeActivity.this, LoginActivity.class));
                Toast.makeText(PatientHomeActivity.this, "User logged out", Toast.LENGTH_SHORT).show();
            }
        });

        setAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientHomeActivity.this, SetAppointment1.class));
            }
        });

        viewAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientHomeActivity.this, ViewAppointmentActivity.class));
            }
        });

        }
}