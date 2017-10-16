package com.hansung.android.project1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantDetail extends AppCompatActivity {
    static MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        // 데이터 원본 준비
        ArrayList<MyItem> data = new ArrayList<MyItem>();
        data.add(new MyItem(R.drawable.katsudo, "가츠동", "5000"));
        data.add(new MyItem(R.drawable.sakedong, "사케동", "7000"));
        data.add(new MyItem(R.drawable.shrimp, "새우튀김", "6000"));
        data.add(new MyItem(R.drawable.sushi, "초밥", "15000"));


        //어댑터 생성
        adapter = new MyAdapter(this, R.layout.activity_restaurant_detail, data);

        //어댑터 연결
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);


    }

    public void callnumber(View view){

        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-760-4499")));  //통화실행코드

    }
}
