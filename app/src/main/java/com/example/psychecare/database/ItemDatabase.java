package com.example.psychecare.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.psychecare.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "item.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    private static final String TABLE_ITEMS = "items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_SOLUTION = "solution";

    // Create table query
    private static final String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_SOLUTION + " TEXT)";

    public ItemDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, item.getTitle());
        values.put(COLUMN_DESCRIPTION, item.getDescription());
        values.put(COLUMN_SOLUTION, item.getSolution());
        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }


    public int updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, item.getTitle());
        values.put(COLUMN_DESCRIPTION, item.getDescription());
        values.put(COLUMN_SOLUTION, item.getSolution());
        return db.update(TABLE_ITEMS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
    }

    public void deleteItem(int itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(itemId)});
        db.close();
    }

    @SuppressLint("Range")
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                item.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                item.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                item.setSolution(cursor.getString(cursor.getColumnIndex(COLUMN_SOLUTION)));
                items.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return items;
    }
    @SuppressLint("Range")
    public Item getItemById(int itemId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Item item = null;
        Cursor cursor = db.query(TABLE_ITEMS, null, COLUMN_ID + "=?",
                new String[]{String.valueOf(itemId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            item = new Item();
            item.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            item.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
            item.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
            item.setSolution(cursor.getString(cursor.getColumnIndex(COLUMN_SOLUTION)));
            cursor.close();
        }

        db.close();
        return item;
    }

    public List<Item> searchItems(String query) {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String searchQuery = "SELECT * FROM " + TABLE_ITEMS + " WHERE " +
                COLUMN_TITLE + " LIKE ? OR " + COLUMN_DESCRIPTION + " LIKE ?";
        Cursor cursor = db.rawQuery(searchQuery, new String[]{"%" + query + "%", "%" + query + "%"});
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                @SuppressLint("Range") String solution = cursor.getString(cursor.getColumnIndex(COLUMN_SOLUTION));
                itemList.add(new Item(id, title, description, solution));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return itemList;
    }

}
