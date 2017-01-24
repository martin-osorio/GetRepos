package com.martinosorio.restpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultAPIDetail extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_apidetail);
        tv = (TextView) findViewById(R.id.content);
        tv.setText(getIntent().getStringExtra("details"));
    }
}
