package com.sgc109.android.randomchatapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChatRoomActivity extends AppCompatActivity {
    private static final String EXTRA = "com.sgc109.android.randomchatapp.extra";

    public static Intent newIntent(Context packageContext){ // 액티비티로 넘기고 싶은 인자들 추가
        Intent intent = new Intent(packageContext, ChatRoomActivity.class);
        intent.putExtra(EXTRA, 1); // extra value 수정
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_chat_room, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_refresh:

            default:
                return true;
        }
    }
}
