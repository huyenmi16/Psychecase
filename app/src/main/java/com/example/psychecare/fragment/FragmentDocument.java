package com.example.psychecare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psychecare.R;
import com.example.psychecare.adapter.AppointmentAdapter;
import com.example.psychecare.adapter.DoctorAdapter;
import com.example.psychecare.database.AppointmentDatabase;
import com.example.psychecare.database.DoctorDatabase;
import com.example.psychecare.models.Appointment;
import com.example.psychecare.models.Doctor;

import java.util.List;

public class FragmentDocument extends Fragment {
    private RecyclerView recyclerView;
    private AppointmentAdapter appointmentAdapter;
    private List<Appointment> appointmentList;
    private AppointmentDatabase appointmentDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_document, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        appointmentDatabase = new AppointmentDatabase(getActivity());
        appointmentList = appointmentDatabase.getAllAppointments(); // Phương thức này cần phải tự triển khai trong DoctorDatabase

        appointmentAdapter = new AppointmentAdapter(getActivity(), appointmentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(appointmentAdapter);

        return view;
    }
}
