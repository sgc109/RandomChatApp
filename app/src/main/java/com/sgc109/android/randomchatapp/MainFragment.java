package com.sgc109.android.randomchatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by sgc109 on 2017-11-30.
 */

public class MainFragment extends Fragment {
    private static final String TAG = "sgc109.MainFragment";
    private static final String DIALOG_PROGRESS = "DialogProgress";
    public static final int REQUEST_DIALOG = 0;

    private FirebaseAuth mAuth;
    private Button mEnterTheRoomButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            mAuth.signInAnonymously().addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInAnonymously:sccess");
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                    } else {
                        Log.w(TAG, "signInAnonymously:failure", task.getException());
                        Toast.makeText(getActivity(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        mEnterTheRoomButton = (Button)view.findViewById(R.id.activity_main_enter_the_room_button);
        mEnterTheRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                ProgressFragment dialog = ProgressFragment.newInstance();
                dialog.setTargetFragment(MainFragment.this, REQUEST_DIALOG);
                dialog.show(manager, DIALOG_PROGRESS);

//                Intent intent = ChatRoomActivity.newIntent(MainActivity.this);
//                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_DIALOG:
                boolean hasFoundOtherUser = data.getBooleanExtra(ProgressFragment.EXTRA_HAS_FOUND_OTHER_USER, false);
                if(hasFoundOtherUser) {
                    Intent intent = ChatRoomActivity.newIntent(getActivity());
                    startActivity(intent);
                }
                return;
            default:
                return;
        }
    }
}
