package com.hansung.android.project1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import org.w3c.dom.Text;

import static android.support.v4.graphics.drawable.DrawableCompat.setTint;

public class MenuDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        //위로 돌아가기 버튼
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            Drawable drawable = ContextCompat.getDrawable(this,R.drawable.ic_chevron_left_black_24dp);
            if (drawable != null) {
                setTint(drawable,Color.WHITE);
                actionBar.setHomeAsUpIndicator(drawable);
            }
        }

        //OnItemClickListener로 부터 보내진 정보를 받아서 xml파일에 적용
        Intent intent =getIntent();
        String msg=intent.getStringExtra("Option1");
        int icon=intent.getIntExtra("Option2",0);
        String price=intent.getStringExtra("Option3");
        String score=intent.getStringExtra("Option4");

        //Image 설정
        ImageView image=(ImageView)findViewById(R.id.image1) ;
        image.setImageResource(icon);

        //음식 이름 설정
        TextView name = (TextView)findViewById(R.id.text1);
        name.setText(msg);

        //가격 설정
        TextView name2 = (TextView)findViewById(R.id.text2);
        name2.setText(price+"원");

        //평점 설정
        TextView name3 = (TextView)findViewById(R.id.text3);
        name3.setText("평점: "+score);

    }

}
