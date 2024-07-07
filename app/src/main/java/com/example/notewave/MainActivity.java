package com.example.notewave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.notewave.Fragments.Home;
import com.example.notewave.Fragments.UpdateNote;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Home home = new Home();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMainActivity,home).commit();
    }

    public void replaceFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMainActivity,fragment).addToBackStack(null).commit();
    }
}