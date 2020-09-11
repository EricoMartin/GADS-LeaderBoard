package com.example.gadsleaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {
    EditText first_name, last_name, email_address, github_link;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        button = findViewById(R.id.button2);
        first_name = findViewById(R.id.editText);
        last_name = findViewById(R.id.editText2);
        email_address = findViewById(R.id.editText3);
        github_link = findViewById(R.id.editText4);

        setSupportActionBar(findViewById(R.id.submission_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void submitDetails(View view) {
        String firstName = first_name.getEditableText().toString().trim();
        String lastName = last_name.getText().toString().trim();
        String email = email_address.getText().toString().trim();
        String link = github_link.getText().toString().trim();

        if (validateInputs(firstName, first_name) && validateInputs(lastName, last_name)
                && validateInputs(email, email_address) && validateInputs(link, github_link)) {

            new AlertDialog.Builder(FormActivity.this)
                    .setTitle("Are you Sure ?")
                    .setPositiveButton("Yes", (dialog, which) -> submitDetails(firstName, lastName, email, link))
                    .create()
                    .show();
        }
    }

    private boolean validateInputs(String value, EditText view) {
        if (value.isEmpty()) {
            view.setError("Field must not be empty");
            view.requestFocus();
            return false;
        } else {
            view.setError(null);
            return true;
        }
    }


    public void submitDetails(String firstName, String lastName, String email, String link) {
                        ApiUtil.submitProject(firstName, lastName, email, link)
                                .enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if (!response.isSuccessful()) {
                                            new AlertDialog.Builder(FormActivity.this)
                                                    .setTitle("Failed to Submit Project")
                                                    .setMessage(response.message() + "\n" + response.code())
                                                    .create()
                                                    .show()
                                            ;
                                        }

                                        new AlertDialog.Builder(FormActivity.this)
                                                .setTitle("Success ")
                                                .setMessage("Project Submitted Successfully")
                                                .create()
                                                .show()
                                        ;
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        new AlertDialog.Builder(FormActivity.this)
                                                .setTitle("Failed to Submit Project")
                                                .setMessage(t.getMessage())
                                                .create()
                                                .show();
                                    }
                                });
                    }


}
