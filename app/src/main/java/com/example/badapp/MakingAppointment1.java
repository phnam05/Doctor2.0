package com.example.badapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MakingAppointment1 extends Fragment {

    Button button;
    Spinner typeSpinner, genderSpinner, timeSpinner;
    ProgressBar progressBar;

    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    CollectionReference appointmentCollection = fStore.collection("appointments");
    EditText editDate, editTextName, editTextNote;

    TextView backPageText;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fStore = FirebaseFirestore.getInstance();
        View view = inflater.inflate(R.layout.fragment_making_appointment1, container, false);

        backPageText = view.findViewById(R.id.backPageText);
        backPageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PatientHomeActivity.class));
            }
        });

//        genderSpinner = (Spinner) view.findViewById(R.id.spinnerGender);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                getActivity(),
//                R.array.gender_array,
//                android.R.layout.simple_spinner_item
//        );
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        genderSpinner.setAdapter(adapter);

        typeSpinner = (Spinner) view.findViewById(R.id.spinnerAppointmentType);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.appointment_array,
                android.R.layout.simple_spinner_item
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter2);

        timeSpinner = (Spinner) view.findViewById(R.id.spinnerTime);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.time_array,
                android.R.layout.simple_spinner_item
        );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter3);

        editDate = (EditText) view.findViewById(R.id.editTextDate);
        editDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chooseDate();
            }
        });

        //Register Appointment & Go to next page
        button = view.findViewById(R.id.buttonNext);
        editTextName = view.findViewById(R.id.editTextName);
        editDate = view.findViewById(R.id.editTextDate);
        editTextNote = view.findViewById(R.id.editNote);
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
                                          appointmentCollection.document(id).set(appointment);

                FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                                          MakingAppointment2 make2 = new MakingAppointment2();
                                          Bundle bundle = new Bundle();
                                          bundle.putSerializable("appointment", appointment);
                                          make2.setArguments(bundle);
                                          ft.replace(android.R.id.content, make2);
                                          ft.addToBackStack(null);
                                          ft.commit();

//                ConfirmationActivity confirmationActivity = new ConfirmationActivity();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("appointment", appointment);
//                confirmationActivity.setArguments(bundle);
//                ft.replace(android.R.id.content, confirmationActivity);
//                ft.addToBackStack(null);
//                ft.commit();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_id, confirmationActivity).commit();
                                      }
                                  }
        );
        return view;
    }
    public MakingAppointment1() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private void chooseDate(){
        // Get a calendar instance
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH); // Use DAY_OF_MONTH instead of DATE
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        // Create a DatePickerDialog and set its OnDateSetListener
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(), // context should be getActivity() when inside a Fragment
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