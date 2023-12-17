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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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
    String currentUserEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        currentUserEmail = fAuth.getCurrentUser().getEmail();
        //Toast.makeText(getApplicationContext(), "Email: " + currentUserEmail,Toast.LENGTH_SHORT).show();
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

//    private void myEventChangeListener(){
//        db.collection().whereEqualTo("email",currentUserEmail)
//    }

    private void eventChangeListener() {

        db.collection("appointments").whereEqualTo("patientEmail",currentUserEmail)
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
                            //Toast.makeText(getApplicationContext(), "Gotcha",Toast.LENGTH_SHORT).show();
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