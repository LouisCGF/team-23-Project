package com.example.team_23_project.Activities;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import com.example.team_23_project.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Activity class for contact_us.xml. Extends AppCompatActivity
 *
 * @author Bogdan Caplan
 * @version 1.0
 *
 */
public class ContactUsActivity extends AppCompatActivity {

    private TextInputLayout emailSubject;
    private TextInputLayout emailMessage;
    private Spinner emailSelector;

    /**
     * Used to start the activity
     *
     * @author Bogdan Caplan
     *
     * @param savedInstanceState reference to a Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);

        String[] emails = getResources().getStringArray(R.array.emails);

        emailSubject = findViewById(R.id.enterSubjectField);
        emailMessage = findViewById(R.id.messageBox);

        emailSelector = findViewById(R.id.emailSelector);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, emails);
        emailSelector.setAdapter(adapter);


        Button buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(v -> sendMail());
    }

    /**
     * Retrieves input from the email selector, subject and message fields and opens an Android mail
     * interface with the input information
     *
     * @author Bogdan Caplan
     *
     */
    private void sendMail() {
        String selectedEmail = emailSelector.getSelectedItem().toString();

        String subject = Objects.requireNonNull(emailSubject.getEditText()).getText().toString();
        String message = Objects.requireNonNull(emailMessage.getEditText()).getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, selectedEmail);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
