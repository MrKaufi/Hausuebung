package com.example.hausuebung19;

import java.util.ArrayList;

public class Tasklist {
    public String taskListTitle;
    public ArrayList<Task> tasks = new ArrayList<>();

    public Tasklist(String taskListTitle) {
        this.taskListTitle = taskListTitle;
    }

    public String getTaskListTitle() {
        return taskListTitle;
    }

    public void setTaskListTitle(String taskListTitle) {
        this.taskListTitle = taskListTitle;
    }

    public int getTaskListSum() {
        return tasks.size();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    public ArrayList<Task> getTasks() { return tasks; }

    @Override
    public String toString() {
        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += tasks.get(i).toString();
            if (i != tasks.size()) tasksString += ";";
        }
        return taskListTitle + ";" + tasksString;
    }
}