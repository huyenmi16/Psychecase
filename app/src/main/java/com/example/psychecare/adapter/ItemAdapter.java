package com.example.psychecare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psychecare.UpdateDeleteActivity;
import com.example.psychecare.models.Item;

import java.util.ArrayList;
import java.util.List;
import com.example.psychecare.R;
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private List<Item> itemsList;
    private Context context;

    public ItemAdapter(Context context) {
        this.context = context;
        this.itemsList = new ArrayList<>();
    }

    public void setItems(List<Item> items) {
        this.itemsList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemsList.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewDescription.setText(item.getDescription());
        holder.textViewSolution.setText(item.getSolution());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mở một Activity hoặc Dialog mới để hiển thị trang layout sửa hoặc xóa
                openEditDeleteActivity(item);
            }
        });
    }
    private void openEditDeleteActivity(Item item) {
        // Tạo Intent để chuyển đến Activity mới và gửi dữ liệu của item được chọn
        Intent intent = new Intent(context, UpdateDeleteActivity.class);
        intent.putExtra("itemId", item.getId()); // Gửi id của item để xác định item cần sửa hoặc xóa
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDescription, textViewSolution;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewSolution = itemView.findViewById(R.id.textViewSolution);

        }
    }
}
