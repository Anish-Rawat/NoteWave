package com.example.notewave.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.notewave.DataModel.NotesEntity;
import com.example.notewave.R;
import com.example.notewave.ViewModel.NotesViewModel;
import com.example.notewave.databinding.FragmentInsertNoteBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InsertNote extends Fragment {

    private FragmentInsertNoteBinding binding;
    private View insertNoteView;
    private String title,subtitle,note,priority="1";
    private NotesViewModel notesViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInsertNoteBinding.inflate(inflater,container,false);
        insertNoteView = binding.getRoot();

        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        binding.floatingBtnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = binding.editTextTitle.getText().toString();
                subtitle = binding.editTextSubtitle.getText().toString();
                note = binding.editTextInsertNote.getText().toString();

                createNote(title,subtitle,note);
            }
        });

        binding.greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriority.setImageResource(R.drawable.check);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);

                priority = "1";
            }
        });

        binding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(R.drawable.check);
                binding.redPriority.setImageResource(0);

                priority = "2";
            }
        });

        binding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(R.drawable.check);

                priority = "3";
            }
        });

        return insertNoteView;
    }

    private void createNote(String title, String subtitle, String note) {

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d,YYYY");
        String currentDate = dateFormat.format(calForDate.getTime());

        notesViewModel.insertNote(new NotesEntity(title,subtitle,note,currentDate,priority));

        Toast.makeText(requireContext(), "Note Created Succesfully", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();
    }
}