package com.example.badapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SetAppointment1 extends AppCompatActivity {
    Button button;
    Spinner typeSpinner, genderSpinner, timeSpinner;
    ProgressBar progressBar;

    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    CollectionReference appointmentCollection = fStore.collection("appointments");
    EditText editDate, editTextName, editTextNote;

    TextView backPageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_appointment);
        fStore = FirebaseFirestore.getInstance();
        backPageText = findViewById(R.id.backPageText);
        backPageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PatientHomeActivity.class));
            }
        });
        typeSpinner = (Spinner) findViewById(R.id.spinnerAppointmentType);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.appointment_array,
                android.R.layout.simple_spinner_item
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter2);

        timeSpinner = (Spinner) findViewById(R.id.spinnerTime);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.time_array,
                android.R.layout.simple_spinner_item
        );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter3);

        editDate = (EditText) findViewById(R.id.editTextDate);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate();
            }
        });

        button = findViewById(R.id.buttonNext);
        editTextName = findViewById(R.id.editTextName);
        editDate = findViewById(R.id.editTextDate);
        editTextNote = findViewById(R.id.editNote);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
                String name = editTextName.getText().toString();
                String date = editDate.getText().toString();
                String typeAppointment = typeSpinner.getSelectedItem().toString();
                String note = editTextNote.getText().toString();
                String time = timeSpinner.getSelectedItem().toString();
                String id = appointmentCollection.document().getId();

                Appointment appointment = new Appointment(name,email,date,typeAppointment,note,time,id);
                //appointmentCollection.document(id).set(appointment);
                Intent intent = (new Intent(SetAppointment1.this,SetAppointment2.class));
                intent.putExtra("appointment",appointment);
                //startActivity(intent);
                startActivity(intent);
                                      }
                                  }
        );

    }
    private void chooseDate(){
        // Get a calendar instance
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH); // Use DAY_OF_MONTH instead of DATE
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        // Create a DatePickerDialog and set its OnDateSetListener
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                SetAppointment1.this, // context should be getActivity() when inside a Fragment
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // Set the calendar to the chosen date
                        calendar.set(selectedYear, selectedMonth, selectedDay);

                        // Format the date as you want it to be
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        editDate.setText(dateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);

        datePickerDialog.show(); // Show the DatePickerDialog
    }
}