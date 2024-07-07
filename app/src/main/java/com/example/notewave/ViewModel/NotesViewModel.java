package com.example.notewave.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notewave.DataModel.NotesEntity;
import com.example.notewave.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<NotesEntity>> getAllNotes;
    public LiveData<List<NotesEntity>> getAllNotesHighToLow;
    public LiveData<List<NotesEntity>> getAllNotesLowToHigh;
    public NotesViewModel(@NonNull Application application) {
        super(application);

        notesRepository = new NotesRepository(application);
        getAllNotes = notesRepository.getAllNotes();
        getAllNotesHighToLow = notesRepository.getAllNotesHighToLow();
        getAllNotesLowToHigh = notesRepository.getAllNotesLowToHigh();
    }

    public void insertNote(NotesEntity notesEntity){

        notesRepository.insertNote(notesEntity);
    }

    public void updateNote(NotesEntity notesEntity){

        notesRepository.updateNote(notesEntity);
    }

    public void deleteNote(int id){

        notesRepository.deleteNote(id);
    }

    public LiveData<List<NotesEntity>> getAllNotes(){
        return getAllNotes;
    }

    public LiveData<List<NotesEntity>> getGetAllNotesHighToLow(){
        return getAllNotesHighToLow;
    }

    public LiveData<List<NotesEntity>> getGetAllNotesLowToHigh(){
        return getAllNotesLowToHigh;
    }

}
