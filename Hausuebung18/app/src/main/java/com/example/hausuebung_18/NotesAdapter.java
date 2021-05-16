package com.example.hausuebung_18;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotesAdapter extends BaseAdapter {
    Date date = new Date();
    List<Note> notes;
    LayoutInflater layoutInflater;
    int ListViewLayoutId;
    DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public NotesAdapter(Context context, int ListViewLayoutId, List<Note> notes) {
        this.notes = notes;
        this.ListViewLayoutId = ListViewLayoutId;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = notes.get(position);
        View listItem = (convertView == null) ?layoutInflater.inflate(this.ListViewLayoutId, null) : convertView;
        ((TextView) listItem.findViewById(R.id.noteDate)).setText(sdf.format(note.getDate()));
        if (date.after(note.getDate())){//I don´t know why this does not work??????????
            listItem.setBackgroundColor(Color.parseColor("#C62727"));
        }
        ((TextView) listItem.findViewById(R.id.noteTitle)).setText(note.getNoteTitle());
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                View mView = layoutInflater.inflate(R.layout.note_onclick_layout, null);

                Button edit = mView.findViewById(R.id.edit);
                Button delete = mView.findViewById(R.id.delete);
                Button details = mView.findViewById(R.id.details);

                dialog.setView(mView);

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                        dialog.setTitle("New Note");
                        View mView = layoutInflater.inflate(R.layout.add_note_layout, null);

                        CalendarView calendarView = mView.findViewById(R.id.calendarView);
                        calendarView.setDate(note.getDate().getTime());

                        TimePicker timePicker = mView.findViewById(R.id.timePicker);
                        timePicker.setHour(note.getDate().getHours());
                        timePicker.setHour(note.getDate().getMinutes());

                        EditText noteTitle =  mView.findViewById(R.id.noteTitle);
                        noteTitle.setText(note.getNoteTitle());
                        EditText noteDescription = mView.findViewById(R.id.noteDescription);
                        noteDescription.setText(note.getNoteText());

                        dialog.setView(mView);
                        dialog.show();
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        notes.remove(note);
                        notifyDataSetChanged();
                    }
                });
                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                        dialog.setTitle("Details");

                        LinearLayout linearLayout = new LinearLayout(v.getContext());

                        TextView details = new TextView(v.getContext());
                        details.setX(90);
                        details.setText(note.getNoteText());

                        linearLayout.addView(details);
                        dialog.setView(linearLayout);

                        dialog.show();
                    }
                });

                dialog.show();
            }
        });
        return listItem;
    }
}
