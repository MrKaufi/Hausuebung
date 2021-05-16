package com.example.hausuebung19;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MySettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Settings");
        setTheme(R.style.PreferenceTheme);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new MySettingsFragment())
                .commit();
    }
}
