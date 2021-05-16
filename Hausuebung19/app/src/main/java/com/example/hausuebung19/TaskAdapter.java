package com.example.hausuebung19;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends BaseAdapter {
    List<Task> tasks;
    LayoutInflater layoutInflater;
    int ListViewLayoutId;
    Context context;

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
        final Task taskList = tasks.get(position);
        View listItem = (convertView == null) ?layoutInflater.inflate(this.ListViewLayoutId, null) : convertView;

        ((TextView) listItem.findViewById(R.id.taskTitle)).setText(taskList.getTaskTitle());
        ((TextView) listItem.findViewById(R.id.taskDate)).setText(String.valueOf(taskList.getTaskDate()));
        listItem.findViewById(R.id.taskCheckBox);

        return listItem;
    }
}
