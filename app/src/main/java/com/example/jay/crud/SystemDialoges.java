package com.example.jay.crud;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SystemDialoges {

    MainActivity mainActivity;

    public void ToastMessages(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void startActivity(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        ((Activity) context).finish();
        context.startActivity(intent);
    }


    public void FillList(ListView listView, ArrayList<Notes> notelist) {
        try {
            NoteListAdapter ListAdapter = new NoteListAdapter(mainActivity.getApplicationContext(), R.layout.note_list_adapter, notelist);
            listView.setAdapter(ListAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Fill List -> ", ex.toString());
        }
    }
}
