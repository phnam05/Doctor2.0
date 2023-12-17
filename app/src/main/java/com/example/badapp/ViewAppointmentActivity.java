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
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAppointmentActivity extends AppCompatActivity {
    ProgressBar progressBar;

    Button backHomeButton;
    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;
    private List<Appointment> appointmentList;
    FirebaseFirestore db;


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
        // Initialize the RecyclerView and set its layout manager
        recyclerView = findViewById(R.id.recycler_view_appointments);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewAppointmentActivity.this,RecyclerView.VERTICAL,false));
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

    //        Doctor doctor1 = new Doctor();
//
//        // Add appointments to the list
//        appointments.add(new Appointment("Alice Brown", "alice.brown@email.com", "15 Jan 2023",
//                "Online", "Please be gentle", "09:00am - 12:00pm",
//                "ID123", doctor1));
//        appointments.add(new Appointment("Bob Smith", "bob.smith@email.com", "20 Jan 2023",
//                "Offline", "Regular check-up", "10:00am - 11:00am",
//                "ID124", doctor1));
//        appointments.add(new Appointment("Charlie Davis", "charlie.davis@email.com", "25 Jan 2023",
//                "Online", "Consultation", "02:00pm - 03:00pm",
//                "ID125", doctor1));
}