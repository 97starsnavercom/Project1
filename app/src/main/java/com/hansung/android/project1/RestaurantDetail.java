package com.hansung.android.project1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RestaurantDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
    }

    public void callnumber(View view){

        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-760-4499")));  //통화실행코드

    }
}
