package com.example.jay.crud;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends ArrayAdapter<Notes> {
    private Context mcontext;
    int mResource;
    public NoteListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Notes> objects) {
        super(context, resource, objects);
        mcontext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String ID = getItem(position).getID();
        String note_head = getItem(position).getNote_head();
        String addedTime = getItem(position).getAddedTime();

        Notes notes = new Notes(note_head,addedTime,ID);

        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView txt_note_head = (TextView) convertView.findViewById(R.id.note_header);
        TextView txt_note_date_time = (TextView) convertView.findViewById(R.id.note_added_time_date);

        txt_note_head.setText(note_head);
        txt_note_date_time.setText(addedTime);

        return convertView;
    }
}
