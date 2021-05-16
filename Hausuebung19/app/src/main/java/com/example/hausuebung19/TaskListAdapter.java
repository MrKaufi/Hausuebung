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
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class TaskListAdapter extends BaseAdapter {
    List<TaskList> taskLists;
    LayoutInflater layoutInflater;
    int ListViewLayoutId;
    Context context;

    public TaskListAdapter(Context context, int ListViewLayoutId, List<TaskList> taskLists) {
        this.context = context;
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

        ((TextView) listItem.findViewById(R.id.taskListTitle)).setText(taskList.getTaskListTitle());
        ((TextView) listItem.findViewById(R.id.taskListSum)).setText(String.valueOf(taskList.getTaskListSum()));

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent taskListActivity = new Intent(context, TaskListActivity.class);
                taskListActivity.putExtra("title", taskList.getTaskListTitle());
                context.startActivity(taskListActivity);
            }
        });
        listItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                View mView = layoutInflater.inflate(R.layout.tasklist_onclick_layout, null);

                Button edit = mView.findViewById(R.id.edit);
                Button delete = mView.findViewById(R.id.delete);

                dialog.setView(mView);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                        dialog.setTitle("Edit Tasklist");
                        View mView = layoutInflater.inflate(R.layout.add_tasklist_layout, null);

                        final EditText taskListTitle =  mView.findViewById(R.id.taskListTitle);
                        taskListTitle.setText(taskList.getTaskListTitle());

                        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                taskList.setTaskListTitle(taskListTitle.getText().toString());
                                dialogInterface.dismiss();
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
                        taskLists.remove(taskList);
                        notifyDataSetChanged();
                    }
                });
                dialog.show();

                return true;
            }
        });
        return listItem;
    }
}