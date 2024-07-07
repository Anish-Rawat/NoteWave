package com.example.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notewave.DataModel.NotesEntity;
import com.example.notewave.Fragments.UpdateNote;
import com.example.notewave.MainActivity;
import com.example.notewave.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<NotesEntity> listOfNotes ;
    private Context context;

    public NotesAdapter(List<NotesEntity> listOfNotes, Context context) {
        this.listOfNotes = listOfNotes;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_card,parent,false);
        NotesViewHolder holder = new NotesViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        NotesEntity obj = listOfNotes.get(position);
        holder.notesTitle.setText(obj.getTitle());
        holder.notesSubtitle.setText(obj.getSubtitle());
        holder.notesDate.setText(obj.getDate());
        if (obj.getPriority().equals("1")){
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        } else if (obj.getPriority().equals("2")) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if (obj.getPriority().equals("3")) {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("id",String.valueOf(obj.getId()));
                bundle.putString("title",obj.getTitle());
                bundle.putString("subtitle",obj.getSubtitle());
                bundle.putString("priority",obj.getPriority());
                bundle.putString("note",obj.getNote());

                UpdateNote updateNote = new UpdateNote();
                updateNote.setArguments(bundle);
                ((MainActivity) context).replaceFragment(updateNote);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfNotes.size();
    }

    public void searchNote(List<NotesEntity> filteredNoteList){

        this.listOfNotes = filteredNoteList;
        notifyDataSetChanged();
    }
    public class NotesViewHolder extends RecyclerView.ViewHolder{

        View notesPriority;
        TextView notesTitle,notesSubtitle,notesDate;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            notesPriority = itemView.findViewById(R.id.notesPriority);
            notesTitle = itemView.findViewById(R.id.notesTitle);
            notesSubtitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesDate);
        }
    }
}
