package com.example.jay.crud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SystemDialoges {

    public void ToastMessages(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void startActivity(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void startActivity(Context context, Class activity,String noteID) {
        Intent intent = new Intent(context, activity);
        intent.putExtra("ITEM_ID",noteID);
        context.startActivity(intent);
    }
}
