package com.example.notewave.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notewave.DataModel.NotesEntity;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    void insertNote(NotesEntity notesEntity);

    @Update
    void updateNote(NotesEntity notesEntity);

    @Query("DELETE FROM Notes WHERE id=:id")
    void deleteNote(int id);

    @Query("SELECT *FROM Notes")
    LiveData<List<NotesEntity>> getAllNotes();

    @Query("SELECT *FROM Notes ORDER BY priority Desc")
    LiveData<List<NotesEntity>> getAllNotesHighToLow();

    @Query("SELECT *FROM Notes ORDER BY priority ASC")
    LiveData<List<NotesEntity>> getAllNotesLowToHigh();
}
