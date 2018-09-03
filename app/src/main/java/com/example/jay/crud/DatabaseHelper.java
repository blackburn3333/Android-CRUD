package com.example.jay.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "note_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "note_head";
    private static final String COL_3 = "addedTime";
    private static final String COL_4 = "note_description";

    public DatabaseHelper(Context context) {

        super(context, TABLE_NAME, null, 9);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        } catch (Exception ex) {
            System.out.print("If Ex -> " + ex);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            String CreateTable = "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_2 + " TEXT NOT NULL,"
                    + COL_4 + " TEXT NOT NULL,"
                    + COL_3 + " TIMESTAMP "+
                    "  DEFAULT CURRENT_TIMESTAMP" + ")";
            db.execSQL(CreateTable);
        } catch (Exception ex) {
            System.out.print("If Create -> " + ex);
        }
    }

    public boolean addDataToDB(String note_head, String note_description) {


        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, note_head);
        contentValues.put(COL_4, note_description);

        long result = database.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDate() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_3 + " DESC";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
