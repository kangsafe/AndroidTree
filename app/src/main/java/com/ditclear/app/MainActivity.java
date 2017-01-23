package com.ditclear.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ks.androidtree.R;

public class MainActivity extends AppCompatActivity {

    private com.ditclear.scratchcard.ScratchCard view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratchcard_main);
    }
}
