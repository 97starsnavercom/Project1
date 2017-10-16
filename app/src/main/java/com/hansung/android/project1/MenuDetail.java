package com.hansung.android.project1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuDetail extends AppCompatActivity {

    private static final String TAG = "ActivityLifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        Intent intent =getIntent();
        String msg=intent.getStringExtra("Option1");
        String price=intent.getStringExtra("Option3");
        int Icon=intent.getIntExtra("Option2",0);

        TextView name = (TextView).findViewById(R.id.text);
        name.setText(msg);


    }


}
