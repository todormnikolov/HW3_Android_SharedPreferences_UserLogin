package com.tnt.android.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText editUsername;
    EditText editUserPass;
    EditText editUserRePass;
    Button btnCreate;
    Button btnCancel;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editUsername = (EditText) findViewById(R.id.edit_username);
        editUserPass = (EditText) findViewById(R.id.edit_user_pass);
        editUserRePass = (EditText) findViewById(R.id.edit_user_re_pass);
        btnCreate = (Button) findViewById(R.id.btn_create);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString().trim();

                Context context = getApplicationContext();

                if (username.length() < 4 || username.length() > 20) {
                    Toast.makeText(context, "Use username from 4 to 20 letters and/or numbers", Toast.LENGTH_LONG).show();
                } else {
                    Context c = SignupActivity.this;
                    sp = c.getSharedPreferences(MainActivity.SP_NAME, MODE_PRIVATE);
                    String usernameSp = sp.getString("username", "");

                    if (!usernameSp.equals("") && username.equals(usernameSp)) {
                        Toast.makeText(context, "The username " + username + " isn\'t available", Toast.LENGTH_LONG).show();
                    } else {
                        String pass = editUserPass.getText().toString();
                        String rePass = editUserRePass.getText().toString();

                        if (pass.length() < 3 || pass.length() > 15) {
                            Toast.makeText(context, "Password is not a valid! Use between 3 and 15 chars", Toast.LENGTH_LONG).show();
                        } else if (!pass.equals(rePass)) {
                            Toast.makeText(context, "The values in fields \"Password\" and \"Confirm Pass\" don\'t match", Toast.LENGTH_LONG).show();
                        } else {
                            sp.edit().putString("username", username).apply();
                            sp.edit().putString("password", pass).apply();

                            Toast.makeText(context, "Username: " + username + " is created", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
