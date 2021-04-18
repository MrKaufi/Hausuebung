package com.example.hausuebung_18;

import java.util.Date;

public class Note {
    public Date date;
    public String noteTitle;
    public String noteText;

    public Note(Date date, String noteTitle, String noteText) {
        this.date = date;
        this.noteTitle = noteTitle;
        this.noteText = noteText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
}
