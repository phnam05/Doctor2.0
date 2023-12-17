package com.example.badapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmationActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmationActivity extends Fragment {

    Button backToHome, setNewAppointment;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConfirmationActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfirmationActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmationActivity newInstance(String param1, String param2) {
        ConfirmationActivity fragment = new ConfirmationActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmation_activity, container, false);

        Appointment appointment = (Appointment)getArguments().getSerializable("appointment");
        String patientName = appointment.getPatientName();
        String date = appointment.getAppointmentDate();
        String time = appointment.getTime();
        String type = appointment.getAppointmentType();
        String note = appointment.getNote();
        TextView appointmentInfo = view.findViewById(R.id.tvAppointmentInfo);
        appointmentInfo.setText("Patient name: " + patientName + "\n" + "Time: "+ date + ", " + time + "\n" +  "Appointment type: "+type + "\n" + "Note: " + note);
        backToHome = view.findViewById(R.id.btnHome);
        //setNewAppointment = view.findViewById(R.id.btnSetNewAppointment);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PatientHomeActivity.class));
            }
        });

//        setNewAppointment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MakingAppointment1 make1 = new MakingAppointment1();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_id,make1).commit();
//            }
//        });
//        // Inflate the layout for this fragment
        return view;
    }
}