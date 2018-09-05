package com.example.jay.crud;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NoteListAdapter extends ArrayAdapter<Notes> {

    private Context mcontext;
    int mResource;
    DatabaseHelper databaseHelper;
    SystemDialoges systemDialoges = new SystemDialoges();
    MainActivity mainActivity = new MainActivity();
    ListView note_list;
    ArrayList<Notes> notesArrayAdapter;

    NoteListAdapter noteListAdapter;


    public NoteListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Notes> objects) {
        super(context, resource, objects);
        mcontext = context;
        mResource = resource;
        databaseHelper = new DatabaseHelper(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        try {
            final String ID = getItem(position).getID();
            String note_head = getItem(position).getNote_head();
            String note_description = getItem(position).getNote_description();
            String addedTime = getItem(position).getAddedTime();


            Notes notes = new Notes(note_head, addedTime, ID, note_description);

            LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
            convertView = layoutInflater.inflate(mResource, parent, false);

            TextView txt_note_head = (TextView) convertView.findViewById(R.id.note_header);
            TextView txt_note_date_time = (TextView) convertView.findViewById(R.id.note_added_time_date);
            TextView txt_note_desc = (TextView) convertView.findViewById(R.id.note_description);
            TextView txt_note_date = (TextView) convertView.findViewById(R.id.note_date);
            ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.deleteButton);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH);
            SimpleDateFormat dayFormatter = new SimpleDateFormat("d", Locale.ENGLISH);

            txt_note_head.setText(note_head);
            txt_note_date_time.setText(formatter.format(new Date()));
            txt_note_desc.setText(note_description);
            txt_note_date.setText(dayFormatter.format(new Date()));

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (databaseHelper.deleteNote(ID)) {
                        systemDialoges.ToastMessages(mcontext, "Note Deleted");
                        noteListAdapter.notifyDataSetChanged();
                    } else {
                        systemDialoges.ToastMessages(mcontext, "Note Not Deleted");
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("List", ex.toString());
        }
        return convertView;
    }

}
