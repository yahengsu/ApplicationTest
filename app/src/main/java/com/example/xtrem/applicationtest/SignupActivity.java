package com.example.xtrem.applicationtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by YaHeng on 2016-10-23.
 */

public class SignupActivity extends AppCompatActivity {
    public Button signUpButton = null;

    public AutoCompleteTextView passwordView = null;
    public AutoCompleteTextView emailView = null;
    public AutoCompleteTextView firstnameView = null;
    public AutoCompleteTextView lastnameView = null;
    public AutoCompleteTextView phonenumberView = null;
    public int test = 12;


    private FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;


    private static final String TAG = "SignupActivity";

    //sets up the xml layout and the Firebase user creation API objects
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_signup_activity);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Us   er is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
        mDatabase = FirebaseDatabase.getInstance().getReference();



        signUpButton = (Button) findViewById(R.id.SignUpActivityButton);


        //when account is created notify the user and move on to login screen
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                createAccount();
                Intent i = new Intent(view.getContext(), LoginActivity.class);
                startActivity(i);
                Toast.makeText(SignupActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.w(TAG, "App destroyed");

        super.onDestroy();
    }


    //method that creates the user account with an email and password, stores all the other user info into the Firebase Database
    public void createAccount() {
        emailView = (AutoCompleteTextView) findViewById(R.id.editEmail);
        passwordView = (AutoCompleteTextView) findViewById(R.id.editPassword);
        firstnameView = (AutoCompleteTextView) findViewById(R.id.editFirstName);
        lastnameView = (AutoCompleteTextView) findViewById(R.id.editLastName);
        phonenumberView = (AutoCompleteTextView) findViewById(R.id.editPhoneNumber);
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        String firstName = firstnameView.getText().toString();
        String lastName = lastnameView.getText().toString();
        String phoneNumber = phonenumberView.getText().toString();
        int numberRides = 0;
        int rating = 0;
        int distance = 0;
       // writeNewUser(firstName,lastName,email,phoneNumber,numberRides,rating,distance);
        mAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });



    }


    //method to add user info to Firebase API, commented out for debugging purposes
   /*

    private void writeNewUser(String firstName, String lastName, String email,String phoneNumber, int numberRides, int rating, int distance) {

        userInfo userData = new userInfo(firstName, lastName,email,phoneNumber, numberRides, rating, distance);

        mDatabase.child("users").child(email).setValue(userData);
    }

    */


}
