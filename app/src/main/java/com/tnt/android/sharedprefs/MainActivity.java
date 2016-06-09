package com.tnt.android.sharedprefs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String SP_NAME = "USER_DETAILS";

    TextView txtError;
    EditText editUser;
    EditText editPass;
    Button btnLogin;
    Button btnSignup;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = (EditText) findViewById(R.id.edit_user);
        editPass = (EditText) findViewById(R.id.edit_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignup = (Button) findViewById(R.id.btn_signup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context c = MainActivity.this;
                sp = c.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
                String wrongUsername = "Wrong username";
                String username = sp.getString("username", wrongUsername);

                String usernameInput = editUser.getText().toString();
                boolean hasError = false;

                if (!usernameInput.equals(username) || usernameInput.equals(wrongUsername)) {
                    hasError = true;
                }

                String wrongPassword = "Wrong password";
                String password = sp.getString("password", wrongPassword);
                String passwordInput = editPass.getText().toString();

                if (!hasError && ( !passwordInput.equals(password) || passwordInput.equals(wrongPassword))){
                    hasError = true;
                }

                if(hasError){
                    Toast.makeText(getApplicationContext(), "Not a correct username and/or password!", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, LoginScreenActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }
}
