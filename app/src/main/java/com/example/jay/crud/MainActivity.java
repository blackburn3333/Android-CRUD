package com.example.jay.crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private Button insert_button;
    private EditText note_text;
    private ListView note_list;
    private EditText note_dec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

        insert_button = (Button) findViewById(R.id.inset_note_button);
        note_text = (EditText) findViewById(R.id.note_title);
        note_list = (ListView) findViewById(R.id.note_list);
        note_dec = (EditText) findViewById(R.id.note_dec);

        insert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = note_text.getText().toString();
                String description = note_dec.getText().toString();
                if (note.length() <= 0 || description.length() <= 0) {
                    ToastMessages("Enter Note Before Insert");
                } else {
                    sendDataToDatabase(note, description);
                }
            }
        });

        fillListView();
    }

    private void sendDataToDatabase(String note, String noet_dec) {
        Boolean reslut = databaseHelper.addDataToDB(note, noet_dec);
        if (reslut) {
            ToastMessages("Inert Successful");
            note_text.setText("");
            note_dec.setText("");
            fillListView();

        } else {
            ToastMessages("Inert Unsuccessful");
        }
    }

    private void ToastMessages(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void fillListView() {
        try{


        Cursor noteData = databaseHelper.getDate();
        ArrayList<Notes> notelist = new ArrayList<>();
        while (noteData.moveToNext()) {

            /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            String DateString = noteData.getString(noteData.getColumnIndex("addedTime"));
            Date date = formatter.parse(DateString);*/

            notelist.add(new Notes(
                    noteData.getString(noteData.getColumnIndex("note_head")),
                    noteData.getString(noteData.getColumnIndex("addedTime")),
                    noteData.getString(noteData.getColumnIndex("ID")),
                    noteData.getString(noteData.getColumnIndex("note_description"))
            ));
        }
        NoteListAdapter noteListAdapter = new NoteListAdapter(this, R.layout.note_list_adapter, notelist);
        note_list.setAdapter(noteListAdapter);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
