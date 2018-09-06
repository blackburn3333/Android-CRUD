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
    ListView note_list;
    LayoutInflater layoutInflater;
    ArrayList<Notes> arrayList;


    public NoteListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Notes> objects) {
        super(context, resource, objects);
        mcontext = context;
        mResource = resource;
        databaseHelper = new DatabaseHelper(context);
        layoutInflater = LayoutInflater.from(context);
        arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        try {
            final String ID = getItem(position).getID();
            String note_head = getItem(position).getNote_head();
            String note_description = getItem(position).getNote_description();
            String addedTime = getItem(position).getAddedTime();
            String ShortDescription;

            Notes notes = new Notes(note_head, addedTime, ID, note_description);

            LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
            convertView = layoutInflater.inflate(mResource, parent, false);

            TextView txt_note_head = (TextView) convertView.findViewById(R.id.note_header);
            TextView txt_note_date_time = (TextView) convertView.findViewById(R.id.note_added_time_date);
            TextView txt_note_desc = (TextView) convertView.findViewById(R.id.note_description);
            TextView txt_note_date = (TextView) convertView.findViewById(R.id.note_date);
            TextView txt_read_more = (TextView) convertView.findViewById(R.id.read_more);

            ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.deleteButton);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyy-MM-dd");

            if(note_description.length() >= 20){
                ShortDescription = note_description.substring(0,20);
                txt_read_more.setVisibility(View.VISIBLE);
            }else {
                ShortDescription =note_description;
                txt_read_more.setVisibility(View.INVISIBLE);
            }

            Date addedDay = dayFormatter.parse(addedTime);
            Date addedyearmonth = formatter.parse(addedTime);

            txt_note_head.setText(note_head);
            txt_note_date_time.setText(formatter.format(addedyearmonth));
            txt_note_desc.setText(ShortDescription + " ....");
            txt_read_more.setText("Read more");
            txt_note_date.setText(dayFormatter.format(addedDay).substring(dayFormatter.format(addedDay).length() -2));

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (databaseHelper.deleteNote(ID)) {
                        systemDialoges.ToastMessages(mcontext, "Note Deleted");
                        MainActivity.getInstance().fillListView();
                    } else {
                        systemDialoges.ToastMessages(mcontext, "Note Not Deleted");
                        MainActivity.getInstance().fillListView();
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("List", ex.toString());
        }
        return convertView;
    }
    public void updateResults(ArrayList<Notes> results) {
        arrayList = results;
        //Triggers the list update
        notifyDataSetChanged();
    }
}
