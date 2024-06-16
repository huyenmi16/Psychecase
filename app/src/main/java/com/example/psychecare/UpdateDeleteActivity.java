package com.example.psychecare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.psychecare.database.ItemDatabase;
import com.example.psychecare.models.Item;

public class UpdateDeleteActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextDes, editTextSo;

    private Button btnSaveChanges, btnDeleteItem, btnCancel;

    private int itemId;
    private ItemDatabase dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        // Initialize views
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDes = findViewById(R.id.editTextDescription);
        editTextSo = findViewById(R.id.editTextSolution);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnDeleteItem = findViewById(R.id.btnDeleteItem);
        btnCancel = findViewById(R.id.btnCancel);

        // Get item id from intent
        Intent intent = getIntent();
        itemId = intent.getIntExtra("itemId", -1);

        // Initialize DatabaseHelper
        dbHelper = new ItemDatabase(this);

        // Load item details
        loadItemDetails();

        // Set click listeners
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItem();
            }
        });

        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadItemDetails() {
        Item item = dbHelper.getItemById(itemId);
        if (item != null) {
            editTextTitle.setText(item.getTitle());
            editTextDes.setText(item.getDescription());
            editTextSo.setText(item.getSolution());

        } else {
            // Show message if item not found
            Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void updateItem() {
        String title = editTextTitle.getText().toString();
        String description = editTextDes.getText().toString();
        String solution = editTextSo.getText().toString();


        if (!title.isEmpty() && !description.isEmpty() && !solution.isEmpty()) {
            Item updatedItem = new Item(itemId, title, description, solution);
            int rowsAffected = dbHelper.updateItem(updatedItem);
            if (rowsAffected > 0) {
                Toast.makeText(this, "Item updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to update item", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter all information", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteItem() {
        dbHelper.deleteItem(itemId);
        Toast.makeText(this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}