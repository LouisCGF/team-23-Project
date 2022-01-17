package com.example.team_23_project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.team_23_project.LoginActivity;
import com.example.team_23_project.R;

public class FAQandQAActivity extends AppCompatActivity {

    TextView titleFaq;
    TextView infoFaq;

    Button backToMainFaq;
    Button contactFromFaq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_and_qa);

        titleFaq = findViewById(R.id.titleFaqTxt2);
        infoFaq = findViewById(R.id.infoFaqTxt2);

        backToMainFaq = findViewById(R.id.backToMainFaqBtn2);
        contactFromFaq = findViewById(R.id.contactFromFaqBtn2);

        backToMainFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQandQAActivity.this.startActivity( new Intent( FAQandQAActivity.this, LoginActivity.class));
            }
        });

        infoFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQandQAActivity.this.startActivity( new Intent( FAQandQAActivity.this, ContactUsActivity.class));
            }
        });
    }
}