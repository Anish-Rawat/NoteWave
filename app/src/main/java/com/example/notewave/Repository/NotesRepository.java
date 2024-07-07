package com.example.notewave.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.notewave.Dao.NotesDao;
import com.example.notewave.DataModel.NotesEntity;
import com.example.notewave.Database.NotesDatabase;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public NotesDatabase notesDatabase;
    public LiveData<List<NotesEntity>> getAllNotes;
    public LiveData<List<NotesEntity>> getAllNotesHighToLow;
    public LiveData<List<NotesEntity>> getAllNotesLowToHigh;


    public NotesRepository(Application application){

        notesDatabase = NotesDatabase.getInstance(application);
        notesDao = notesDatabase.notesDao();
        getAllNotes = notesDao.getAllNotes();
        getAllNotesHighToLow = notesDao.getAllNotesHighToLow();
        getAllNotesLowToHigh = notesDao.getAllNotesLowToHigh();
    }

    public void insertNote(NotesEntity notesEntity){

//        notesDao.insertNote(notesEntity);
        new InsertNoteAsyncTask(notesDao).execute(notesEntity);
    }

    public void updateNote(NotesEntity notesEntity){

//        notesDao.updateNote(notesEntity);
        new UpdateNoteAsyncTask(notesDao).execute(notesEntity);
    }

    public void deleteNote(int id){

//        notesDao.deleteNote(id);
        new DeleteNoteAsyncTask(notesDao).execute(id);
    }

    public LiveData<List<NotesEntity>> getAllNotes(){
        return getAllNotes;
    }

    public LiveData<List<NotesEntity>> getAllNotesHighToLow(){
        return getAllNotesHighToLow;
    }

    public LiveData<List<NotesEntity>> getAllNotesLowToHigh(){
        return getAllNotesLowToHigh;
    }
    private static class InsertNoteAsyncTask extends AsyncTask<NotesEntity, Void, Void> {
        private NotesDao notesDao;

        private InsertNoteAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(NotesEntity... notesEntities) {
            notesDao.insertNote(notesEntities[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<NotesEntity, Void, Void> {
        private NotesDao notesDao;

        private UpdateNoteAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(NotesEntity... notesEntities) {
            notesDao.updateNote(notesEntities[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private NotesDao notesDao;

        private DeleteNoteAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Integer... ids) {
            notesDao.deleteNote(ids[0]);
            return null;
        }
    }
}
