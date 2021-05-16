package com.example.hausuebung19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    File file;
    ArrayList<TaskList> taskLists;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskLists = new ArrayList<>();
        file = new File(this.getFilesDir(), "taskLists.datei");
        listView = findViewById(R.id.listView);

        //loadNotesFromCSV();
        loadTaskListsIntoListView();
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
                alertDialogBuilder.setTitle("New Tasklist");
                View mView = getLayoutInflater().inflate(R.layout.add_tasklist_layout, null);
                final EditText noteTitle = mView.findViewById(R.id.taskListTitle);
                alertDialogBuilder.setView(mView);

                alertDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (noteTitle.getText() != null){
                            taskLists.add(new TaskList(noteTitle.getText().toString()));
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
                break;
            case R.id.menuSave:
                saveTaskLists();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadTaskListsIntoListView(){
        TaskListAdapter taskListAdapter = new TaskListAdapter(this, R.layout.tasklist_layout, taskLists);
        taskListAdapter.notifyDataSetChanged();
        listView.setAdapter(taskListAdapter);
    }

    public void saveTaskLists() {
        try {
            FileOutputStream fileOutputStream = openFileOutput("file", MODE_PRIVATE);
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(fileOutputStream));

            for (TaskList t : taskLists) {
                printWriter.println(t.toString());
            }
            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /*
    public void loadNotesFromCSV(){//does not work???
        try {
            ArrayList<TaskList> tempTaskLists = new ArrayList<>();
            String tempString;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (bufferedReader.ready()){
                tempString = bufferedReader.readLine();
                String[] tempValues = tempString.split(";");
                tempTaskLists.add(tempValues[0]);
            }
            taskLists = tempTaskLists;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    */
}