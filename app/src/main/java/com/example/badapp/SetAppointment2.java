package com.example.badapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetAppointment2 extends AppCompatActivity {
    Button setAppointmentButton;
    private TextView backPageText;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    //private List<Doctor> doctorsList; // Member variable for the doctors list
    private DoctorsAdapter doctorsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_appointment2);
        setAppointmentButton = findViewById(R.id.set_appointment_button);
        setAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetAppointment2.this, ConfirmationActivity.class));
            }
        });
        backPageText = findViewById(R.id.backPageText);
        backPageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetAppointment2.this,SetAppointment1.class));
            }
        });
        recyclerView = findViewById(R.id.recycler_view_doctors);
        recyclerView.setLayoutManager(new LinearLayoutManager(SetAppointment2.this,RecyclerView.VERTICAL,false));

        List<Doctor> doctorList = fetchDoctorsFromFirestore();
        doctorsAdapter = new DoctorsAdapter(doctorList, new DoctorsAdapter.OnDoctorClickListener() {
            @Override
            public void onDoctorClick(Doctor doctor) {
                for (Doctor d : doctorList) {
                    d.setSelected(false); // deselect all
                }
                doctor.setSelected(true); // select the clicked one

                // Refresh the adapter
                doctorsAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(doctorsAdapter);



    }
    private List<Doctor> fetchDoctorsFromFirestore() {
        List<Doctor> doctorList_ = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("doctors").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    Doctor doctor = documentSnapshot.toObject(Doctor.class);
                    //doctor.setId(documentSnapshot.getId());
                    doctorList_.add(doctor);
                }
            }
        });
        return doctorList_;
    }
    private List<Doctor> getListDoctorFake(){
        List<Doctor> doctorsList = new ArrayList<>();
        doctorsList.add(new Doctor("Dr. Edward", "Dentist", 50, 5, "+0930 235 049", "edward.dr@gmail.com", "Singapore"));
        doctorsList.add(new Doctor("Dr. Thanh", "Physician", 30, 10, "+0320 809 202", "drthanh@mail.com", "Ho Chi Minh"));
        doctorsList.add(new Doctor("Dr. Anna", "Cardiologist", 20, 5, "+0634 825 837", "annadr@hotmail.com", "Hanoi"));
        doctorsList.add(new Doctor("Dr. David", "General", 40, 15, "+0923 284 732", "doctordavid@gmail.com", "China"));
        doctorsList.add(new Doctor("Dr. Charlie", "Surgeon", 30, 5, "+0378 826 937", "charliedr@gmail.com", "New York"));
        return doctorsList;
    }


}