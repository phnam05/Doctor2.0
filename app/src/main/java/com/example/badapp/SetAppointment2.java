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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

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
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference doctorCollection = db.collection("doctors");
    Appointment appointment;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_appointment2);
        setAppointmentButton = findViewById(R.id.set_appointment_button);
        appointment = (Appointment) getIntent().getSerializableExtra("appointment");
        setAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetAppointment2.this, ConfirmAppointmentActivity.class);
                intent.putExtra("appointment",appointment);
                startActivity(intent);
            }
        });
        backPageText = findViewById(R.id.backPageText);
        backPageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SetAppointment2.this,SetAppointment1.class);
               // intent.putExtra("appointment",appointment);
                startActivity(intent);
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
        db.collection("doctors").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for (DocumentChange dc: value.getDocumentChanges()){
                    if(dc.getType()== DocumentChange.Type.ADDED){
                        Doctor doctor = dc.getDocument().toObject(Doctor.class);
                        doctorList_.add(doctor);
                    }
                    doctorsAdapter.notifyDataSetChanged();
                }
            }
        });
        return doctorList_;
    }
    private List<Doctor> getListDoctorFake(){
        List<Doctor> doctorsList = new ArrayList<>();
        doctorsList.add(new Doctor());
        doctorsList.add(new Doctor());
        doctorsList.add(new Doctor());
        doctorsList.add(new Doctor());

//        doctorsList.add(new Doctor("Dr. Edward", "Dentist", 50, 5, "+0930 235 049", "edward.dr@gmail.com", "Singapore"));
//        doctorsList.add(new Doctor("Dr. Thanh", "Physician", 30, 10, "+0320 809 202", "drthanh@mail.com", "Ho Chi Minh"));
//        doctorsList.add(new Doctor("Dr. Anna", "Cardiologist", 20, 5, "+0634 825 837", "annadr@hotmail.com", "Hanoi"));
//        doctorsList.add(new Doctor("Dr. David", "General", 40, 15, "+0923 284 732", "doctordavid@gmail.com", "China"));
//        doctorsList.add(new Doctor("Dr. Charlie", "Surgeon", 30, 5, "+0378 826 937", "charliedr@gmail.com", "New York"));
        return doctorsList;
    }


}