package com.example.psychecare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psychecare.DetailActivity;
import com.example.psychecare.R;
import com.example.psychecare.models.Doctor;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {
    private Context context;
    private List<Doctor> doctorList;

    public DoctorAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.tvName.setText(doctor.getName());
        holder.tvSpecialty.setText(doctor.getSpecialty());
        // Load ảnh bác sĩ vào ImageView ở đây nếu bạn đã có URL hoặc ID hình ảnh

        // Xử lý sự kiện click cho Button "Xem thêm"
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy vị trí của bác sĩ trong danh sách
                int position = holder.getAdapterPosition();

                // Kiểm tra xem vị trí có hợp lệ không
                if (position != RecyclerView.NO_POSITION) {
                    // Lấy thông tin của bác sĩ từ danh sách
                    Doctor doctor = doctorList.get(position);

                    // Tạo Intent để chuyển đến Activity chi tiết và chuyển dữ liệu
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("doctor_id", doctor.getId());
                    // Gửi dữ liệu khác của bác sĩ nếu cần thiết

                    // Khởi chạy Activity chi tiết
                    context.startActivity(intent);
                }
            }
        });

        // Xử lý sự kiện click cho ImageButton "Yêu thích"
        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi người dùng nhấn vào biểu tượng "Yêu thích" ở đây
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvSpecialty;
        Button btnMore;
        ImageButton btnFavorite;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSpecialty = itemView.findViewById(R.id.tvSpecialty);
            btnMore = itemView.findViewById(R.id.btnMore);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
        }
    }
}
