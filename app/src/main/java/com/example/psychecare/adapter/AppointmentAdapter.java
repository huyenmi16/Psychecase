package com.example.psychecare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psychecare.models.Appointment;

import java.util.List;
import com.example.psychecare.R;
public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private Context context;
    private List<Appointment> appointmentList;

    // Constructor to initialize the context and appointment list
    public AppointmentAdapter(Context context, List<Appointment> appointmentList) {
        this.context = context;
        this.appointmentList = appointmentList;
    }

    // ViewHolder class to hold the views for each appointment item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDoctorName;
        TextView tvAppointmentDate;
        TextView tvAppointmentTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDoctorName = itemView.findViewById(R.id.textViewDoctorName);
            tvAppointmentDate = itemView.findViewById(R.id.textViewAppointmentDate);
            tvAppointmentTime = itemView.findViewById(R.id.textViewAppointmentTime);
            // Add more TextViews for other appointment details if needed
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the appointment_item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the appointment at the given position
        Appointment appointment = appointmentList.get(position);

        // Bind appointment data to the views in the ViewHolder
        holder.tvDoctorName.setText(appointment.getDoctorName());
        holder.tvAppointmentDate.setText(appointment.getAppointmentDate());
        holder.tvAppointmentTime.setText(appointment.getAppointmentTime());
        // Bind more appointment details if added to the layout
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }
}
