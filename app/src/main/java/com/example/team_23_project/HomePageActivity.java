package com.example.team_23_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.team_23_project.Activities.FAQandQAActivity;

public class HomePageActivity extends AppCompatActivity {

    CardView searchForUni;
    CardView settings;
    CardView faqs;
    CardView logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        searchForUni = findViewById(R.id.searchUniBtn);
        settings = findViewById(R.id.settingBtn);
        faqs = findViewById(R.id.faqsBtn);
        logOut = findViewById(R.id.logOutBtn);

        searchForUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, SearchBar.class));
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, UserSettings.class));
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, LoginMain.class));
            }
        });

        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePageActivity.this.startActivity(new Intent(HomePageActivity.this, FAQandQAActivity.class));
            }
        });

    }
}