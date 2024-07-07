package com.example.notewave.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.Adapter.NotesAdapter;
import com.example.notewave.DataModel.NotesEntity;
import com.example.notewave.MainActivity;
import com.example.notewave.R;
import com.example.notewave.ViewModel.NotesViewModel;
import com.example.notewave.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private FragmentHomeBinding binding;
    private View homeView;
    private NotesViewModel notesViewModel;
    private NotesAdapter notesAdapter;
    private List<NotesEntity> allNotesList ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);
        homeView = binding.getRoot();
        setHasOptionsMenu(true);

        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        binding.noFilter.setBackgroundResource(R.drawable.selected_filter_bg);

        binding.floatingBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertNote insertNote = new InsertNote();
                ((MainActivity) requireActivity()).replaceFragment(insertNote);
            }
        });

        binding.noFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.noFilter.setBackgroundResource(R.drawable.selected_filter_bg);
                binding.hightolowFilter.setBackgroundResource(R.drawable.unselected_filter_bg);
                binding.lowtohighFilter.setBackgroundResource(R.drawable.unselected_filter_bg);

                notesViewModel.getAllNotes.observe(requireActivity(), new Observer<List<NotesEntity>>() {
                    @Override
                    public void onChanged(List<NotesEntity> notesEntities) {
                        setAdapter(notesEntities);
                        allNotesList = notesEntities;
                    }
                });
            }
        });

        binding.hightolowFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.noFilter.setBackgroundResource(R.drawable.unselected_filter_bg);
                binding.hightolowFilter.setBackgroundResource(R.drawable.selected_filter_bg);
                binding.lowtohighFilter.setBackgroundResource(R.drawable.unselected_filter_bg);

                notesViewModel.getAllNotesHighToLow.observe(requireActivity(), new Observer<List<NotesEntity>>() {
                    @Override
                    public void onChanged(List<NotesEntity> notesEntities) {
                        setAdapter(notesEntities);
                        allNotesList = notesEntities;
                    }
                });
            }
        });

        binding.lowtohighFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.noFilter.setBackgroundResource(R.drawable.unselected_filter_bg);
                binding.hightolowFilter.setBackgroundResource(R.drawable.unselected_filter_bg);
                binding.lowtohighFilter.setBackgroundResource(R.drawable.selected_filter_bg);

                notesViewModel.getAllNotesLowToHigh.observe(requireActivity(), new Observer<List<NotesEntity>>() {
                    @Override
                    public void onChanged(List<NotesEntity> notesEntities) {
                        setAdapter(notesEntities);
                        allNotesList = notesEntities;
                    }
                });
            }
        });

        // By Default
        notesViewModel.getAllNotes.observe(requireActivity(), new Observer<List<NotesEntity>>() {
            @Override
            public void onChanged(List<NotesEntity> notesEntities) {
                setAdapter(notesEntities);
                allNotesList = notesEntities;
            }
        });

        return homeView;
    }

    private void setAdapter(List<NotesEntity> notesEntities) {
        notesAdapter = new NotesAdapter(notesEntities,requireContext());
        binding.notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        binding.notesRecyclerView.setAdapter(notesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // By Default
        notesViewModel.getAllNotes.observe(requireActivity(), new Observer<List<NotesEntity>>() {
            @Override
            public void onChanged(List<NotesEntity> notesEntities) {
                setAdapter(notesEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu,menu);

        MenuItem search = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) search.getActionView();

        searchView.setQueryHint("Search Your Note....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                List<NotesEntity> filteredNotesList = new ArrayList<>();

                for (NotesEntity notes : allNotesList){

                    if (notes.getTitle().toLowerCase().contains(newText.toLowerCase()) || notes.getSubtitle().toLowerCase().contains(newText.toLowerCase())){

                        filteredNotesList.add(notes);
                    }
                }
                notesAdapter.searchNote(filteredNotesList);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}