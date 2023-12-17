package com.example.badapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConfirmAppointmentActivity extends AppCompatActivity {
    Button backToHome, setNewAppointment;
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    CollectionReference appointmentCollection = fStore.collection("appointments");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_appointment);
        Appointment appointment = (Appointment)getIntent().getSerializableExtra("appointment");
        String patientName = appointment.getPatientName();
        String date = appointment.getAppointmentDate();
        String email = appointment.getPatientEmail();
        String time = appointment.getTime();
        String type = appointment.getAppointmentType();
        String note = appointment.getNote();
        TextView appointmentInfo = findViewById(R.id.tvAppointmentInfo);
        appointmentInfo.setText("Patient name: " + patientName + "\n" + "Time: "+ date + ", " + time + "\n" +  "Appointment type: "+type + "\n" + "Note: " + note);
        backToHome = findViewById(R.id.btnHome);
        appointmentCollection.document().set(appointment);
        //setNewAppointment = view.findViewById(R.id.btnSetNewAppointment);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfirmAppointmentActivity.this, PatientHomeActivity.class));
            }
        });
    }
}