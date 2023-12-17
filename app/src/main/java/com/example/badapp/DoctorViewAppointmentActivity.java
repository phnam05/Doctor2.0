package com.example.badapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DoctorViewAppointmentActivity extends AppCompatActivity {
    ProgressBar progressBar;

    Button backHomeButton;
    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;
    private List<Appointment> appointmentList;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_appointment);
        backHomeButton = findViewById(R.id.btnHome);
        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorViewAppointmentActivity.this, PatientHomeActivity.class));
            }
        });
        // Initialize the RecyclerView and set its layout manager
        recyclerView = findViewById(R.id.recycler_view_appointments);
        recyclerView.setLayoutManager(new LinearLayoutManager(DoctorViewAppointmentActivity.this,RecyclerView.VERTICAL,false));
        db = FirebaseFirestore.getInstance();

        appointmentList = new ArrayList<>();
        adapter = new AppointmentAdapter(appointmentList);
        recyclerView.setAdapter(adapter);
        eventChangeListener();
    }
    private void eventChangeListener() {

        db.collection("appointments")
                .orderBy("appointmentDate", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                Appointment appointment = dc.getDocument().toObject(Appointment.class);
                                appointmentList.add(appointment);
                            }
                            adapter.notifyDataSetChanged();
                        }

                        // Notify the adapter or UI that the data has changed
                    }
                });

    }
}