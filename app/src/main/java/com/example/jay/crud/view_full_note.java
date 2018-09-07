package com.example.jay.crud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class view_full_note extends AppCompatActivity {

    String TAG = "view_full_note";
    Context context;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    SystemDialoges systemDialoges = new SystemDialoges();
    MainActivity mainActivity = new MainActivity();




    String noteHead;
    String noteDescription;
    String noteAddedDateTime;

    private TextView txt_itemID;

    private TextView txt_note_head;
    private TextView txt_note_description;
    private TextView txt_note_added_date;

    private ImageButton button_delete;
    private ImageButton button_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_note);

        final String ItemID = getIntent().getStringExtra("ITEM_ID");
        //txt_itemID = (TextView) findViewById(R.id.itemID);
        //txt_itemID.setText(ItemID);

        txt_note_head = (TextView) findViewById(R.id.NoteTitle);
        txt_note_description = (TextView) findViewById(R.id.NoteDec);
        txt_note_added_date = (TextView) findViewById(R.id.NoteAddedDate);

        button_delete = (ImageButton) findViewById(R.id.delete_note);
        button_update = (ImageButton) findViewById(R.id.update_note);

        getNoteDate(ItemID);

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseHelper.deleteNote(ItemID)){
                   systemDialoges.ToastMessages(getBaseContext(),"Item Delete Successful");
                   systemDialoges.startActivity(getApplicationContext(),MainActivity.class);
                }else {
                    Log.d(TAG,"Delete Error");
                }
            }
        });
    }

    private void getNoteDate(String noteID) {
        Cursor noteData = databaseHelper.getData();
        if (noteData.moveToNext()) {
            noteHead = noteData.getString(1);
            noteDescription = noteData.getString(2);
            noteAddedDateTime = noteData.getString(3);
            Log.d("DATA -> ", noteHead + " " + noteAddedDateTime + " " + noteAddedDateTime);

            txt_note_head.setText(noteHead);
            txt_note_description.setText(noteDescription);
            txt_note_added_date.setText(noteAddedDateTime);

        } else {
            Log.d(TAG, "No Data for id -> " + noteID);
        }
    }
}
