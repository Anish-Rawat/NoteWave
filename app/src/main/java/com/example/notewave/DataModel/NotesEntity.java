package com.example.notewave.DataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes")
public class NotesEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id ;

    @ColumnInfo(name = "Title")
    private String title;

    @ColumnInfo(name = "Subtitle")
    private String subtitle;

    @ColumnInfo(name = "Note")
    private String note;

    @ColumnInfo(name = "Date")
    private String date;

    @ColumnInfo(name = "priority")
    private String priority;

    public NotesEntity() {
    }

    public NotesEntity(int id, String title, String subtitle, String note, String date, String priority) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.note = note;
        this.date = date;
        this.priority = priority;
    }

    @Ignore
    public NotesEntity(String title, String subtitle, String note, String date, String priority) {
        this.title = title;
        this.subtitle = subtitle;
        this.note = note;
        this.date = date;
        this.priority = priority;
    }

    @Ignore
    public NotesEntity(String title, String subtitle, String note) {
        this.title = title;
        this.subtitle = subtitle;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
