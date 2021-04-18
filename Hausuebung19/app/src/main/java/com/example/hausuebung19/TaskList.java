package com.example.hausuebung19;

import java.util.ArrayList;
import java.util.Date;

public class TaskList {
    public String taskListTitle;
    public int taskListSum;

    ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(String taskListTitle, int taskListSum) {
        this.taskListTitle = taskListTitle;
        this.taskListSum = taskListSum;
    }

    public String getTaskListTitle() {
        return taskListTitle;
    }

    public void setTaskListTitle(String taskListTitle) {
        this.taskListTitle = taskListTitle;
    }

    public int getTaskListSum() {
        return taskListSum;
    }

    public void setTaskListSum(int taskListSum) {
        this.taskListSum = taskListSum;
    }

    public void addTask(Task task){
        tasks.add(task);
    }
    public void removeTask(Task task){
        tasks.remove(task);
    }
}

