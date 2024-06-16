package com.example.psychecare;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.psychecare.database.ItemDatabase;
import com.example.psychecare.models.Item;

public class AddActivity extends AppCompatActivity {

    private EditText etTitle, etDescription, etSolution;
    private Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Initialize views
        etTitle = findViewById(R.id.editTextTitle);
        etDescription = findViewById(R.id.editTextDescription);
        etSolution = findViewById(R.id.editTextSolution);
        btnAddItem = findViewById(R.id.buttonSave);

        // Set click listener for add button
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values


                // Add the new item to the database
                addItemToDatabase();

            }
        });

    }
    private void addItemToDatabase() {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        String solution = etSolution.getText().toString();
        if (!title.isEmpty() && !description.isEmpty() && !solution.isEmpty() ) {
            Item newItem = new Item();
            newItem.setTitle(title);
            newItem.setDescription(description);
            newItem.setSolution(solution);
            // Create a new instance of the ItemDatabase
            ItemDatabase itemDatabase = new ItemDatabase(AddActivity.this);

            // Add the item to the database
            itemDatabase.addItem(newItem);
            finish();
        }else
        {
            Toast.makeText(this, "Please enter all information", Toast.LENGTH_SHORT).show();
        }


    }
}
