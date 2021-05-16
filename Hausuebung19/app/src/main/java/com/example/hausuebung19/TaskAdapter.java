package com.example.hausuebung19;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskAdapter extends BaseAdapter {
    List<Task> tasks;
    LayoutInflater layoutInflater;
    int ListViewLayoutId;
    Context context;
    Date date = new Date();

    public TaskAdapter(Context context, int ListViewLayoutId, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
        this.ListViewLayoutId = ListViewLayoutId;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Task task = tasks.get(position);
        View listItem = (convertView == null) ?layoutInflater.inflate(this.ListViewLayoutId, null) : convertView;

        ((TextView) listItem.findViewById(R.id.taskTitle)).setText(task.getTaskTitle());
        ((TextView) listItem.findViewById(R.id.taskDate)).setText(MainActivity.sdf.format(task.getTaskDate()));
        listItem.findViewById(R.id.taskCheckBox);

        CheckBox cb = listItem.findViewById(R.id.taskCheckBox);
        cb.setChecked(task.done);
        cb.setOnClickListener(v -> task.setDone(((CheckBox) v).isChecked()));

        listItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext(), R.style.MyDialogTheme);
                View mView = layoutInflater.inflate(R.layout.tasklist_onclick_layout, null);

                Button edit = mView.findViewById(R.id.edit);
                Button delete = mView.findViewById(R.id.delete);

                dialog.setView(mView);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext(), R.style.MyDialogTheme);
                        dialog.setTitle("Edit Task");
                        View mView = layoutInflater.inflate(R.layout.add_task_layout, null);

                        CalendarView calendarView = mView.findViewById(R.id.calendarView);
                        calendarView.setDate(task.getTaskDate().getTime());
                        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                date = new Date(year,month,dayOfMonth);
                            }
                        });

                        TimePicker timePicker = mView.findViewById(R.id.timePicker);
                        timePicker.setHour(task.getTaskDate().getHours());
                        timePicker.setHour(task.getTaskDate().getMinutes());

                        EditText taskTitle =  mView.findViewById(R.id.taskTitle);
                        taskTitle.setX(80);
                        taskTitle.setText(task.getTaskTitle());

                        dialog.setView(mView);

                        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                task.setTaskTitle(taskTitle.getText().toString());
                                Date fullDate = new Date(date.getTime() + timePicker.getHour() * 3600000 + timePicker.getMinute() * 60000);
                                task.setTaskDate(fullDate);
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        });
                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        dialog.setView(mView);
                        dialog.show();
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tasks.remove(task);
                        notifyDataSetChanged();
                        MainActivity.mainActivity.saveTaskLists();
                    }
                });
                dialog.show();

                return true;
            }
        });

        return listItem;
    }
}
