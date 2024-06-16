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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psychecare.DetailActivity;
import com.example.psychecare.DetailItemActivity;
import com.example.psychecare.models.Doctor;
import com.example.psychecare.models.Item;
import com.example.psychecare.R;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<Item> cardDataList;
    private Context context;

    public CardAdapter(List<Item> cardDataList, Context context) {
        this.cardDataList = cardDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Item cardData = cardDataList.get(position);
        holder.textViewTitle.setText(cardData.getTitle());
        holder.textViewDescription.setText(cardData.getDescription());
        holder.textViewSolution.setText(cardData.getSolution());

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy vị trí của bác sĩ trong danh sách
                int position = holder.getAdapterPosition();

                // Kiểm tra xem vị trí có hợp lệ không
                if (position != RecyclerView.NO_POSITION) {
                    // Lấy thông tin của bác sĩ từ danh sách
                    Item item = cardDataList.get(position);

                    // Tạo Intent để chuyển đến Activity chi tiết và chuyển dữ liệu
                    Intent intent = new Intent(context, DetailItemActivity.class);
                    intent.putExtra("item_id", item.getId());
                    // Gửi dữ liệu khác của bác sĩ nếu cần thiết

                    // Khởi chạy Activity chi tiết
                    context.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return cardDataList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewDescription;
        public TextView textViewSolution;
        public Button btnMore;


        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewSolution = itemView.findViewById(R.id.textViewSolution);
            btnMore = itemView.findViewById(R.id.btnMore);

        }
    }
}
