package com.example.hausuebung19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Date;

public class TaskListActivity extends AppCompatActivity {
    ArrayList<Task> tasks = new ArrayList<>();
    int id;
    ListView listView;
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        listView = findViewById(R.id.tasksListView);

        id = getIntent().getExtras().getInt("id");
        setTitle(getIntent().getExtras().getString("title"));
        tasks = (ArrayList<Task>) getIntent().getSerializableExtra("tasks");
        loadTasksIntoListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tasklist_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.addTaskButton:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
                alertDialogBuilder.setTitle("New Task");
                View mView = getLayoutInflater().inflate(R.layout.add_task_layout, null);
                final CalendarView calendarView = mView.findViewById(R.id.calendarView);
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        date = new Date(year,month,dayOfMonth);
                    }
                });
                final TimePicker timePicker = mView.findViewById(R.id.timePicker);
                final EditText taskTitle = mView.findViewById(R.id.taskTitle);
                taskTitle.setX(80);

                alertDialogBuilder.setView(mView);
                alertDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (taskTitle.getText() != null){
                            try {
                                Date fullDate = new Date(date.getTime() + timePicker.getHour() * 3600000 + timePicker.getMinute() * 60000);
                                tasks.add(new Task(taskTitle.getText().toString(), fullDate, false));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            loadTasksIntoListView();
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        MainActivity.mainActivity.overwriteTasklist(id, tasks);
        MainActivity.mainActivity.saveTaskLists();
        finish();
    }

    public void loadTasksIntoListView(){
        TaskAdapter taskAdapter = new TaskAdapter(this, R.layout.task_layout, tasks);
        taskAdapter.notifyDataSetChanged();
        listView.setAdapter(taskAdapter);
    }
}