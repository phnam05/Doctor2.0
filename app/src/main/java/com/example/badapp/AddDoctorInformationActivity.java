package com.example.badapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddDoctorInformationActivity extends AppCompatActivity {

    EditText editTextSpecialty, editTextYears, editTextNumPatients, editTextLocation;
    Button submitButton;
    ProgressBar progressBar;
    Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor_information);
        doctor = (Doctor) getIntent().getSerializableExtra("doctor");
        editTextSpecialty = findViewById(R.id.editTextSpecialty);
        editTextYears = findViewById(R.id.editTextYearsExperience);
        submitButton = findViewById(R.id.submitButton);
        editTextNumPatients = findViewById(R.id.editTextNumPatient);
        progressBar = findViewById(R.id.progressBar);
        editTextLocation = findViewById(R.id.editTextLocation);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String specialty = editTextSpecialty.getText().toString();
                String years = editTextYears.getText().toString();
                String numPatients = editTextNumPatients.getText().toString();
                String location = editTextLocation.getText().toString();

                doctor.setSpecialty(specialty);
                doctor.setExperienceYears(Integer.parseInt(years));
                doctor.setPatientCount(Integer.parseInt(numPatients));
                doctor.setLocation(location);
                setContentView(R.layout.activity_add_doctor_information);
                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                FirebaseFirestore fStore= FirebaseFirestore.getInstance();
                DocumentReference doctorReference = fStore.collection("doctors").document(fAuth.getCurrentUser().getEmail());
                doctorReference.set(doctor).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("REGISTER", "Success: A new PATIENT account is created for ID");
                    }
                });
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(),"User registered successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }
}