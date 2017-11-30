package com.sgc109.android.randomchatapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sgc109 on 2017-10-29.
 */

public class ProgressFragment extends DialogFragment {
    private static final String TAG = "ProgressFragment";
    private static final String BUNDLE_IS_WAITING = "is_waiting";
    public static final String EXTRA_HAS_FOUND_OTHER_USER = "extra.has_found_other_user";
    private boolean mIsWaiting;


    public static ProgressFragment newInstance() {
        ProgressFragment fragment = new ProgressFragment();
        return fragment;
    }

    public void sendResult(int result, boolean hasFoundOtherUser){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_HAS_FOUND_OTHER_USER, hasFoundOtherUser);
        getTargetFragment().onActivityResult(getTargetRequestCode(), result, intent);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Log.d(TAG, "onCreateDialog");

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_progress, null);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.enter_the_room_button_text)
                .setView(v)
                .create();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.d(TAG, "onDestoryView()");
        sendResult(Activity.RESULT_OK, true);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        Log.d(TAG, "onSaveInstanceState");
        bundle.putBoolean(BUNDLE_IS_WAITING, mIsWaiting);
    }

}
