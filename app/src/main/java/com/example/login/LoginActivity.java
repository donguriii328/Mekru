package com.example.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);

        Button Createbtn = findViewById(R.id.create_btn);
        Createbtn.setOnClickListener((View v) -> {
            Intent intent = new Intent(getApplication(), CreateActivity.class);
            startActivity(intent);
        });


        Button signInbtn = findViewById(R.id.sign_in_btn);
        signInbtn.setOnClickListener((View v) -> {
            String email = ((TextView) findViewById(R.id.email_txt)).getText().toString();
            String password = ((TextView) findViewById(R.id.password_txt)).getText().toString();
            Log.i(TAG, String.format("signin email=%s, password%s", email, password));
            signInWithEmailAndPassword(email, password);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void signInWithEmailAndPassword(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent intent = new Intent(getApplication(), UserActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed",Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }


    private void updateUI(FirebaseUser user) {
        /*Log.i(TAG, String.format("update ui user=%s", user.email.toString()));
        (findViewById<TextView>(R.id.lblUser) as TextView).text = user.email.toString();*/
    }
    private void reload() { }


}




