package com.example.hausuebung19;

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
    List<TaskList> taskLists;
    LayoutInflater layoutInflater;
    int ListViewLayoutId;
    DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public NotesAdapter(Context context, int ListViewLayoutId, List<TaskList> taskLists) {
        this.taskLists = taskLists;
        this.ListViewLayoutId = ListViewLayoutId;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return taskLists.size();
    }

    @Override
    public Object getItem(int position) {
        return taskLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TaskList taskList = taskLists.get(position);
        View listItem = (convertView == null) ?layoutInflater.inflate(this.ListViewLayoutId, null) : convertView;
        ((TextView) listItem.findViewById(R.id.taskListDate)).setText(sdf.format(taskList.getDate()));
        if (date.after(taskList.getDate())){//I don´t know why this does not work??????????
            listItem.setBackgroundColor(Color.parseColor("#C62727"));
        }
        ((TextView) listItem.findViewById(R.id.taskTitle)).setText(taskList.getTaskListTitle());
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                View mView = layoutInflater.inflate(R.layout.task_onclick_layout, null);

                Button edit = mView.findViewById(R.id.edit);
                Button delete = mView.findViewById(R.id.delete);
                Button details = mView.findViewById(R.id.details);

                dialog.setView(mView);

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                        dialog.setTitle("New TaskList");
                        View mView = layoutInflater.inflate(R.layout.add_task_layout, null);

                        CalendarView calendarView = mView.findViewById(R.id.calendarView);
                        calendarView.setDate(taskList.getDate().getTime());

                        TimePicker timePicker = mView.findViewById(R.id.timePicker);
                        timePicker.setHour(taskList.getDate().getHours());
                        timePicker.setHour(taskList.getDate().getMinutes());

                        EditText noteTitle =  mView.findViewById(R.id.taskTitle);
                        noteTitle.setText(taskList.getTaskListTitle());
                        EditText noteDescription =mView.findViewById(R.id.taskDescription);
                        noteDescription.setText(taskList.getNoteText());

                        dialog.setView(mView);
                        dialog.show();
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        taskLists.remove(taskList);
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
                        details.setText(taskList.getNoteText());

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