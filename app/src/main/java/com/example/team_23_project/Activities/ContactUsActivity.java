package com.example.team_23_project.Activities;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import com.example.team_23_project.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ContactUsActivity extends AppCompatActivity {

    private TextInputLayout mEditTextSubject;
    private TextInputLayout mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);

        String[] emails = getResources().getStringArray(R.array.emails);

        mEditTextSubject = findViewById(R.id.enterSubjectField);
        mEditTextMessage = findViewById(R.id.messageBox);

        Spinner editText = findViewById(R.id.emailSelector);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, emails);
        editText.setAdapter(adapter);


        Button buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String[] emails = getResources().getStringArray(R.array.emails);

        String subject = mEditTextSubject.getEditText().getText().toString();
        String message = mEditTextMessage.getEditText().getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, emails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

}
