package com.example.psychecare.fragment;

// FragmentSearch.java


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.psychecare.adapter.SearchResultsAdapter;
import com.example.psychecare.database.AppointmentDatabase;
import com.example.psychecare.database.ItemDatabase;
import com.example.psychecare.models.Item;
import com.example.psychecare.R;
import java.util.List;

public class FragmentSearch extends Fragment {
    private RecyclerView recyclerView;
    private SearchResultsAdapter adapter;
    private List<Item> itemList;
    private ItemDatabase itemDatabase;
//    private AppointmentDatabase appointmentDatabase;
    private SearchView searchView;
    private TextView statisticsTextView;
//    private TextView appointmentCountTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        itemDatabase = new ItemDatabase(getContext());
//        appointmentDatabase = new AppointmentDatabase(getContext());
        itemList = itemDatabase.getAllItems();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SearchResultsAdapter(itemList);
        recyclerView.setAdapter(adapter);

        statisticsTextView = view.findViewById(R.id.statisticsTextView);
//        appointmentCountTextView = view.findViewById(R.id.appointmentCountTextView);
        updateStatistics();



        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchItems(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchItems(newText);
                return false;
            }
        });

        return view;
    }

    private void searchItems(String query) {
        List<Item> filteredItemList = itemDatabase.searchItems(query);
        adapter.updateList(filteredItemList);
        updateStatistics();
//        updateStatistics2(query);
    }

    private void updateStatistics() {
        int count = adapter.getItemCount();
        statisticsTextView.setText("Số lượng bài viết: " + count);
    }
//    private void updateStatistics2(String query) {
//        int appointmentCount = appointmentDatabase.countAppointments(query);
//        appointmentCountTextView.setText("Số lượng ca mắc: " + appointmentCount);
//    }
}
