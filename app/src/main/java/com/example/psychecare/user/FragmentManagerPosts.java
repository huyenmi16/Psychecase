package com.example.psychecare.user;

import android.annotation.SuppressLint;
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
import com.example.psychecare.adapter.ItemAdapter;
import com.example.psychecare.database.ItemDatabase;
import com.example.psychecare.models.Item;

import java.util.List;

public class FragmentManagerPosts extends Fragment {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ItemDatabase databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_managerpost, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemAdapter(getContext());
        recyclerView.setAdapter(adapter);

        databaseHelper = new ItemDatabase(getContext());

        loadPosts();

        return view;
    }

    // Phương thức để tải danh sách bài hát từ cơ sở dữ liệu và hiển thị lên RecyclerView
    private void loadPosts() {
        List<Item> postsList = databaseHelper.getAllItems();
        adapter.setItems(postsList);
    }
}
