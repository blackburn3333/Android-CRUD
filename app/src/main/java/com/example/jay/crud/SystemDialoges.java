package com.example.jay.crud;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class SystemDialoges {

    public void ToastMessages(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
