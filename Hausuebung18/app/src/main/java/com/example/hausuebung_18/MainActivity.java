package com.example.hausuebung_18;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy");
    File file;
    ArrayList<Note> notes;
    ListView listView;

    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = new ArrayList<>();
        file = new File(this.getFilesDir(), "notes.csv");
        listView = findViewById(R.id.listView);

        loadNotesFromCSV();
        loadNotesIntoListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuCreate:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("New Note");
                View mView = getLayoutInflater().inflate(R.layout.add_note_layout, null);
                CalendarView calendarView = (CalendarView) mView.findViewById(R.id.calendarView);
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        date = new Date(year,month,dayOfMonth);
                    }
                });
                TimePicker timePicker = (TimePicker) mView.findViewById(R.id.timePicker);
                EditText noteTitle = (EditText) mView.findViewById(R.id.noteTitle);
                EditText noteDescription = (EditText) mView.findViewById(R.id.noteDescription);
                alertDialogBuilder.setView(mView);

                alertDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (noteTitle.getText() != null && noteDescription.getText() != null){
                            try {
                                Date fullDate = new Date(date.getTime() + timePicker.getHour() * 3600000 + timePicker.getMinute() * 60000);
                                notes.add(new Note(fullDate, noteTitle.getText().toString() ,noteDescription.getText().toString()));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            loadNotesIntoListView();
                            dialogInterface.dismiss();
                        }
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.show();
                break;
            case R.id.menuSafe:
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    for (int i = 0; i < notes.size(); i++) {
                        bufferedWriter.write(notes.get(i).date.toString() + ';' + notes.get(i).noteTitle + ';' + notes.get(i).noteText);
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadNotesIntoListView(){
        NotesAdapter notesAdapter = new NotesAdapter(this, R.layout.note_layout, notes);
        listView.setAdapter(notesAdapter);
    }

    public void loadNotesFromCSV(){//does not work???
        try {
            ArrayList<Note> tempNotes = new ArrayList<>();
            String tempString;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (bufferedReader.ready()){
                tempString = bufferedReader.readLine();
                String[] tempValues = tempString.split(";");
                tempNotes.add(new Note(sdf.parse(tempValues[0]), tempValues[1], tempValues[2]));
            }
            notes = tempNotes;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}