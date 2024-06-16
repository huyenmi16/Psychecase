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
import com.example.psychecare.adapter.DoctorAdapter;
import com.example.psychecare.database.DoctorDatabase;
import com.example.psychecare.models.Doctor;

import java.util.List;


public class FragmentHome extends Fragment {
    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;
    private List<Doctor> doctorList;
    private DoctorDatabase doctorDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        doctorDatabase = new DoctorDatabase(getActivity());
        doctorList = doctorDatabase.getAllDoctors(); // Phương thức này cần phải tự triển khai trong DoctorDatabase

        doctorAdapter = new DoctorAdapter(getActivity(), doctorList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(doctorAdapter);

        return view;
    }



}
