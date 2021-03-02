package com.ktc.todyinfo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.ktc.todyinfo.R;
import com.ktc.todyinfo.widget.HeaderBarView;

public class HomepageActivity extends AppCompatActivity {

    private HeaderBarView headerBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initView();
    }

    private void initView() {
        headerBarView = findViewById(R.id.view_header_bar);
        headerBarView.setLeftListener((v) -> {
            Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
        });
        headerBarView.setRightListener((v) -> {
            Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
        });
    }
}