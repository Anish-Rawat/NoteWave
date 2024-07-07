package com.example.notewave.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notewave.DataModel.NotesEntity;
import com.example.notewave.R;
import com.example.notewave.ViewModel.NotesViewModel;
import com.example.notewave.databinding.FragmentUpdateNoteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateNote extends Fragment {

    private FragmentUpdateNoteBinding binding;
    private View updateNoteView;
    private String title,subtitle,note,priority="1";
    private int id;
    private NotesViewModel notesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUpdateNoteBinding.inflate(inflater,container,false);
        updateNoteView = binding.getRoot();
        setHasOptionsMenu(true);

        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        Bundle args = getArguments();

        if (args!=null){

            Log.d("Update Note", "onClick: bundle is "+ args);

            id = Integer.parseInt(args.getString("id"));
            title = args.getString("title");
            subtitle = args.getString("subtitle");
            note = args.getString("note");
            priority = args.getString("priority");
        }else {
            Log.d("Update Note", "onClick: bundle is "+ args);
        }

        binding.editTextTitleUpdate.setText(title);
        binding.editTextSubtitleUpdate.setText(subtitle);
        binding.editTextUpdateNote.setText(note);
        switch (priority){
            case "1":
                binding.greenPriorityUpdate.setImageResource(R.drawable.check);
                binding.yellowPriorityUpdate.setImageResource(0);
                binding.redPriorityUpdate.setImageResource(0);
                break;

            case "2":
                binding.greenPriorityUpdate.setImageResource(0);
                binding.yellowPriorityUpdate.setImageResource(R.drawable.check);
                binding.redPriorityUpdate.setImageResource(0);
                break;

            case "3":
                binding.greenPriorityUpdate.setImageResource(0);
                binding.yellowPriorityUpdate.setImageResource(0);
                binding.redPriorityUpdate.setImageResource(R.drawable.check);
                break;

            default:
                Log.d("Update Note", "onCreateView: priority Value is "+priority);
        }


        binding.floatingBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTitle = binding.editTextTitleUpdate.getText().toString();
                String updatedSubtitle = binding.editTextSubtitleUpdate.getText().toString();
                String updatedNote = binding.editTextUpdateNote.getText().toString();

                updateNote(id,updatedTitle,updatedSubtitle,updatedNote,priority);
            }
        });

        binding.greenPriorityUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriorityUpdate.setImageResource(R.drawable.check);
                binding.yellowPriorityUpdate.setImageResource(0);
                binding.redPriorityUpdate.setImageResource(0);

                priority = "1";
            }
        });

        binding.yellowPriorityUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriorityUpdate.setImageResource(0);
                binding.yellowPriorityUpdate.setImageResource(R.drawable.check);
                binding.redPriorityUpdate.setImageResource(0);

                priority = "2";
            }
        });

        binding.redPriorityUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriorityUpdate.setImageResource(0);
                binding.yellowPriorityUpdate.setImageResource(0);
                binding.redPriorityUpdate.setImageResource(R.drawable.check);

                priority = "3";
            }
        });

        return updateNoteView;
    }
    private void updateNote(int id, String title, String subtitle, String note, String priority) {

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d,YYYY");
        String date = dateFormat.format(calForDate.getTime());

        notesViewModel.updateNote(new NotesEntity(id,title,subtitle,note,date,priority));

        Toast.makeText(requireContext(), "Note Updated Succesfully", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.delete_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.delete){

            BottomSheetDialog sheetDialog = new BottomSheetDialog(requireContext(),R.style.BottomSheetStyle);

            View view = LayoutInflater.from(requireContext()).inflate(R.layout.delete_bottom_sheet,null);

            sheetDialog.setContentView(view);
            sheetDialog.show();

            TextView no_delete,yes_delete;
            no_delete = view.findViewById(R.id.no_delete);
            yes_delete = view.findViewById(R.id.yes_delete);

            no_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sheetDialog.dismiss();
                }
            });

            yes_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notesViewModel.deleteNote(id);
                    sheetDialog.dismiss();
                    getActivity().onBackPressed();
                }
            });
        }

        return true;
    }
}