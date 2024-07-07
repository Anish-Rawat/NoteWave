package com.example.notewave.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notewave.Dao.NotesDao;
import com.example.notewave.DataModel.NotesEntity;

@Database(entities = NotesEntity.class , version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    private static final String DB_NAME = "NotesDatabse";
    private static volatile NotesDatabase instance = null;

    public static synchronized NotesDatabase getInstance(Context context){

        if (instance==null){
            instance = Room.databaseBuilder(context,NotesDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }

        return instance;
    }

    public abstract NotesDao notesDao();
}
