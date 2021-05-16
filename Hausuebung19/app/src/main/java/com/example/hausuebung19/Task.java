package com.example.hausuebung19;

import java.util.Date;

public class Task {
    String TaskTitle;
    Date TaskDate;
    boolean done = false;


    public Task(String taskTitle, Date taskDate) {
        TaskTitle = taskTitle;
        TaskDate = taskDate;
        this.done = done;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public Date getTaskDate() {
        return TaskDate;
    }

    public void setTaskDate(Date taskDate) {
        TaskDate = taskDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return getTaskTitle() + ";" + getTaskDate();
    }
}
