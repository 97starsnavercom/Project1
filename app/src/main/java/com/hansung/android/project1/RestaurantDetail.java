package com.hansung.android.project1;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RestaurantDetail extends AppCompatActivity {
    static MyAdapter adapter;

    private File mPhotoFile =null;
    private String mPhotoFileName = null;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    //통화실행코드
    public void callnumber(View view){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-760-4499")));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_restaurant);


        // 데이터 원본 준비
        ArrayList<MyItem> data = new ArrayList<MyItem>();
        data.add(new MyItem(R.drawable.katsudo, "가츠동", "5000","3.1"));
        data.add(new MyItem(R.drawable.sakedong, "사케동", "7000","4.7"));
        data.add(new MyItem(R.drawable.shrimp, "새우튀김", "6000","2.2"));
        data.add(new MyItem(R.drawable.sushi, "초밥", "15000","3.4"));


        //어댑터 생성
        adapter = new MyAdapter(this, R.layout.item, data);

        //어댑터 연결
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        //Listner 부착
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked, int position, long id) {

                String name = ((MyItem)adapter.getItem(position)).nName;
                int Icon=((MyItem)adapter.getItem(position)).mIcon;
                String Price=((MyItem)adapter.getItem(position)).nPrice;
                String Score=((MyItem)adapter.getItem(position)).nScore;

                //MenuDetail에 속성값 전달
                Intent intent=new Intent(getApplicationContext(),MenuDetail.class);
                intent.putExtra("Option1",name);
                intent.putExtra("Option2",Icon);
                intent.putExtra("Option3",Price);
                intent.putExtra("Option4",Score);
                startActivity(intent);

            }
        });
    }

    public void restaurant_image(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //1. 카메라 앱으로 찍은 이미지를 저장할 파일 객체 생성
            mPhotoFileName = "IMG"+currentDateFormat()+".jpg";
            mPhotoFile = new File("data/data/com.hansung.android.project1/files/", mPhotoFileName);

            if (mPhotoFile !=null) {
                //2. 생성된 파일 객체에 대한 Uri 객체를 얻기
                Uri imageUri = FileProvider.getUriForFile(this, "com.example.kwanwoo.multimediatest", mPhotoFile);

                //3. Uri 객체를 Extras를 통해 카메라 앱으로 전달
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else
                Toast.makeText(getApplicationContext(), "file null", Toast.LENGTH_SHORT).show();
        }

    }

    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

}
