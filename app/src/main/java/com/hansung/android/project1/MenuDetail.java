package com.hansung.android.project1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import org.w3c.dom.Text;

public class MenuDetail extends AppCompatActivity {

    private static final String TAG = "ActivityLifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_detail);


        Intent intent =getIntent();
        String msg=intent.getStringExtra("Option1");
        String price=intent.getStringExtra("Option3");
        String score=intent.getStringExtra("Option4");
        TextView name = (TextView)findViewById(R.id.text1);
        name.setText(msg);
        TextView name2 = (TextView)findViewById(R.id.text2);
        name2.setText(price);
        TextView name3 = (TextView)findViewById(R.id.text3);
        name3.setText(score);
        ImageView imageView=(ImageView)findViewById(R.id.image1) ;
        if(msg.equals("가츠동")){
            imageView.setImageResource(R.drawable.katsudo);
        }
        else if(msg.equals("사케동")){
            imageView.setImageResource(R.drawable.sakedong);
        }
        else if(msg.equals("새우튀김")){
            imageView.setImageResource(R.drawable.shrimp);
        }
        else if(msg.equals("초밥")){
            imageView.setImageResource(R.drawable.sushi);
        }







    }


}
