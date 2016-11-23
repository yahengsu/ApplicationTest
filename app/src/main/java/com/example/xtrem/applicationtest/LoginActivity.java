    package com.example.xtrem.applicationtest;


    import android.content.Intent;
    import android.support.annotation.NonNull;
    import android.support.v7.app.AppCompatActivity;


    import android.os.Bundle;

    import android.util.Log;
    import android.view.View;
    import android.view.View.OnClickListener;

    import android.widget.AutoCompleteTextView;
    import android.widget.Button;

    import android.widget.Toast;





    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.AuthResult;

    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;



    /**
     * A login screen that offers login via email/password.
     */
    public class LoginActivity extends AppCompatActivity  {


        // UI references.
       private AutoCompleteTextView emailView = null;
        private AutoCompleteTextView passwordView = null;
        private FirebaseAuth mAuth;
        private FirebaseAuth.AuthStateListener mAuthListener;
        private Button mEmailSignInButton = null;



        private static final String TAG = "LoginActivity";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            //new Firebase API authorization method creation
            mAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    } else {
                        // User is signed out
                        Log.d(TAG, "onAuthStateChanged:signed_out");
                    }

                }
            };


            //attaches variables to the xml layout elements
            mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
            mEmailSignInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View view) {
                    emailView = (AutoCompleteTextView) findViewById(R.id.email);
                    passwordView = (AutoCompleteTextView) findViewById(R.id.password);

                    String email = emailView.getText().toString();
                    String password = passwordView.getText().toString();


                    //checks user info to see if it is correct, if so then logs user in and moves to next activity
                    Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                    Intent i = new Intent(view.getContext(),MainActivity.class);
                                    startActivity(i);

                                    // If sign in fails, display a message to the user
                                    // If sign in succeeds the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Log.w(TAG, "signInWithEmail:failed", task.getException());
                                        Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                                Toast.LENGTH_SHORT).show();
                                        Intent n = new Intent(view.getContext(),LoginActivity.class);
                                        startActivity(n);
                                    }

                                    // ...
                                }
                            });
                }
            });


        }



        //methods below are so the application doesnt break, adds and removes the authorization listener

        @Override
        public void onStart() {
            super.onStart();
            mAuth.addAuthStateListener(mAuthListener);


        }

        @Override
        public void onStop() {
            super.onStop();
            if (mAuthListener != null) {
                mAuth.removeAuthStateListener(mAuthListener);
                FirebaseAuth.getInstance().signOut();
            }

        }

        @Override
        protected void onDestroy() {
            Log.w(TAG, "App destroyed");
            super.onDestroy();
        }

    }


