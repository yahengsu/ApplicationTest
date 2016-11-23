package com.example.xtrem.applicationtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * Created by YaHeng on 2016-10-23.
 */

public class StartPageActivity extends AppCompatActivity {

    private Button loginButton = null;
    private Button signUpButton = null;
    private final String TAG = null;


    //first screen that user is introduced to, sets up the xml layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);


        //move user to login screen when login button is clicked

    loginButton = (Button) findViewById(R.id.LoginButton);

    loginButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
           Intent i = new Intent(v.getContext(),LoginActivity.class);
            startActivity(i);
        }
    });


        //move uesr to signup screen when signup button is clicked
        signUpButton = (Button) findViewById(R.id.SignUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent j = new Intent(v.getContext(),SignupActivity.class);
                startActivity(j);
            }

        });


    }



    //methods to ensure app doesnt break
    @Override
    protected void onStop() {
        Log.w(TAG, "App stopped");

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.w(TAG, "App destroyed");

        super.onDestroy();
    }

}
