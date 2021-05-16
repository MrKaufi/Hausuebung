package com.example.hausuebung19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    static DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    static File file;
    static ArrayList<Tasklist> tasklists;
    ListView listView;

    static MainActivity mainActivity;
    private final static int RQ_PREFERENCES = 1;
    private static final int RQ_WRITE_STORAGE = 12345;
    private SharedPreferences prefs;
    private SharedPreferences .OnSharedPreferenceChangeListener preferenceChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tasklists = new ArrayList<>();
        file = new File(this.getFilesDir(), "tasklists.myfile");
        listView = findViewById(R.id.listView);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        preferenceChangeListener = (sharedPrefs, key) -> preferenceChanged(sharedPrefs,key);
        prefs .registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        mainActivity = this;

        preferenceChanged(prefs, "darkThemeKey");
        loadTasksFromFile();
        loadTaskListsIntoListView();
    }

    private void preferenceChanged(SharedPreferences sharedPrefs, String key) {
        boolean darkTheme = sharedPrefs.getBoolean(key, false);
        if (darkTheme){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addListButton:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
                alertDialogBuilder.setTitle("New Tasklist");
                View mView = getLayoutInflater().inflate(R.layout.add_tasklist_layout, null);
                final EditText noteTitle = mView.findViewById(R.id.taskListTitle);
                noteTitle.setX(80);
                alertDialogBuilder.setView(mView);

                alertDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (noteTitle.getText() != null) {
                            tasklists.add(new Tasklist(noteTitle.getText().toString()));
                            loadTaskListsIntoListView();
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
                saveTaskLists();
                break;

            case R.id.preferencesButton:
                    Intent intent = new Intent(this, MySettingsActivity.class);
                    startActivityForResult(intent, RQ_PREFERENCES);
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadTaskListsIntoListView() {
        TaskListAdapter taskListAdapter = new TaskListAdapter(this, R.layout.tasklist_layout, tasklists);
        taskListAdapter.notifyDataSetChanged();
        listView.setAdapter(taskListAdapter);
    }

    public void saveTaskLists() {
        try {
            FileOutputStream fileOutputStream = openFileOutput("tasklists.myfile", MODE_PRIVATE);
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(fileOutputStream));

            for (Tasklist t : tasklists) {
                printWriter.println(t.toString());
            }
            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void saveTaskListsToJson() {

    }

    public void loadTasksFromFile() {
        ArrayList<Tasklist> tempTasklists = new ArrayList<>();
        Tasklist tempTasklist;

        String taskTitle = "";
        Date date = null;
        boolean done = false;
        String line;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("tasklists.myfile")))) {
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split(";");
                tempTasklist = new Tasklist(splittedLine[0]);
                if (splittedLine.length >= 2){
                    for (int i = 1; i < splittedLine.length; i++) {
                        taskTitle = splittedLine[i];
                        i++;
                        date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(splittedLine[i]);
                        i++;
                        if (splittedLine[i].equals("true")) done = true;
                        else done = false;
                        tempTasklist.addTask(new Task(taskTitle, date, done));
                    }
                }
                tempTasklists.add(tempTasklist);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        tasklists = tempTasklists;
        loadTaskListsIntoListView();
    }

    private void loadFromJson() throws IOException, JSONException {
        String content = "";

        AssetManager assetManager = getAssets();
        InputStream inputStream = assetManager.open("movies.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String currentLine;
        while ((currentLine = reader.readLine()) != null){
            content += currentLine;
        }

        JSONObject jsonObject = new JSONObject(content);
        jsonArray = jsonObject.getJSONArray("results");
    }

    public void overwriteTasklist(int id, ArrayList<Task> tasks) {
        tasklists.get(id).tasks = tasks;
        loadTaskListsIntoListView();
    }
}