package com.example.badapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddDoctorInformationActivity extends AppCompatActivity {

    EditText editTextSpecialty, editTextYears, editTextNumPatients, editTextLocation;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Doctor doctor = (Doctor) getIntent().getSerializableExtra("doctor");
        editTextSpecialty = findViewById(R.id.editTextSpecialty);
        editTextYears = findViewById(R.id.editTextYearsExperience);
        editTextNumPatients = findViewById(R.id.editTextNumPatient);
        editTextLocation = findViewById(R.id.editTextLocation);

        String specialty = editTextSpecialty.getText().toString();
        String years = editTextYears.getText().toString();
        String numPatients = editTextNumPatients.getText().toString();
        String location = editTextLocation.getText().toString();
        doctor.setSpecialty(specialty);
        doctor.setExperienceYears(years);
        doctor.setPatientCount(numPatients);
        doctor.setLocation(location);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor_information);
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fStore= FirebaseFirestore.getInstance();
        DocumentReference doctorReference = fStore.collection("doctors").document(fAuth.getCurrentUser().getEmail());
        //doctorReference.
    }
}