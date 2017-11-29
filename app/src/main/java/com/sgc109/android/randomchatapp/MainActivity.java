
package com.sgc109.android.randomchatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private Button mEnterTheRoomButton;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInAnonymously:sccess");
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                    } else {
                        Log.w(TAG, "signInAnonymously:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        mProgressBar = (ProgressBar)findViewById(R.id.activity_main_progress_bar);
        mEnterTheRoomButton = (Button)findViewById(R.id.activity_main_enter_the_room_button);
        mEnterTheRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
//                Intent intent = ChatRoomActivity.newIntent(MainActivity.this);
//                startActivity(intent);
            }
        });
    }
}
