
package com.sgc109.android.randomchatapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment(){
        return new MainFragment();
    }
}
