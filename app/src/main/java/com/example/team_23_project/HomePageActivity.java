package com.example.team_23_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.team_23_project.Activities.FAQandQAActivity;

public class HomePageActivity extends AppCompatActivity {

    CardView searchForUni;
    CardView settings;
    CardView faqs;
    CardView logOut;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        welcome = findViewById(R.id.welcomeTxt);
        searchForUni = findViewById(R.id.searchUniBtn);
        settings = findViewById(R.id.settingBtn);
        faqs = findViewById(R.id.faqsBtn);
        logOut = findViewById(R.id.logOutBtn);

        searchForUni.setOnClickListener(v -> HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, SearchBar.class)));

        settings.setOnClickListener(v -> HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, SettingsActivity.class)));

        logOut.setOnClickListener(v -> HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, LoginActivity.class)));

        faqs.setOnClickListener(v -> HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, FAQandQAActivity.class)));

    }
}