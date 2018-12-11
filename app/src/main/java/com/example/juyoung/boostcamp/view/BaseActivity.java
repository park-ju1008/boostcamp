package com.example.juyoung.boostcamp.view;


import android.support.v7.app.AppCompatActivity;

import com.example.juyoung.boostcamp.MyApplication;

public class BaseActivity extends AppCompatActivity {
    public void progressON() {
        MyApplication.getInstance().progressON(this, null);
    }

    public void progressON(String message) {
        MyApplication.getInstance().progressON(this, message);
    }

    public void progressOFF() {
        MyApplication.getInstance().progressOFF();
    }


}

