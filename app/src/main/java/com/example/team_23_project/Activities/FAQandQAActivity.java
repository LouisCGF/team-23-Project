package com.example.team_23_project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.team_23_project.R;

public class FAQandQAActivity extends AppCompatActivity {

    Button contactFromFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_and_qa);

        contactFromFaq = findViewById(R.id.contactFromFaqBtn2);

        contactFromFaq.setOnClickListener(v -> FAQandQAActivity.this.startActivity( new Intent( FAQandQAActivity.this, ContactUsActivity.class)));
    }
}