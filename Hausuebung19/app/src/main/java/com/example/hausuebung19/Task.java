package com.example.hausuebung19;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    String taskTitle;
    Date taskDate;
    boolean done = false;

    public Task(String taskTitle, Date taskDate, boolean done) {
        this.taskTitle = taskTitle;
        this.taskDate = taskDate;
        this.done = done;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        if (done) return getTaskTitle() + ";" + getTaskDate().toString() + ";" + "true";
        return  getTaskTitle() + ";" + getTaskDate() + ";" + "false";
    }
}
