package com.hansung.android.project1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/***********************************************************************************
 *                                                                                 *
 *   출처:https://github.com/kwanulee/Android/blob/master/examples/SQLiteDBTest    *
 *       :https://github.com/kwanulee/Android/blob/master/examples/MultimediaTest  *
 *                                                                                 *
 ***********************************************************************************/

public class RestaurantDetail extends AppCompatActivity {
    static MyAdapter adapter;

    private File mPhotoFile =null;
    private String mPhotoFileName = null;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private DBHelper mDbHelper;


    //통화실행코드
    public void callnumber(View view){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-760-4499")));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_restaurant);
        checkDangerousPermissions();



    }

    public void restaurant_image(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);



        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //1. 카메라 앱으로 찍은 이미지를 저장할 파일 객체 생성
            mPhotoFileName = "IMG"+currentDateFormat()+".jpg";
            mPhotoFile = new File(Environment.getExternalStorageDirectory().getPath()+"/Pictures/", mPhotoFileName);

            if (mPhotoFile !=null) {
                //2. 생성된 파일 객체에 대한 Uri 객체를 얻기
                Uri imageUri = FileProvider.getUriForFile(this, "com.hansung.android.project1", mPhotoFile);
                Log.i("asd",imageUri.toString());

                //3. Uri 객체를 Extras를 통해 카메라 앱으로 전달
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else
                Toast.makeText(getApplicationContext(), "file null", Toast.LENGTH_SHORT).show();
        }

    }

    public void restaurant_restaurant(View view){
        mDbHelper = new DBHelper(this);
        EditText editText1 = (EditText)findViewById(R.id.name);
        EditText editText2 = (EditText)findViewById(R.id.add);
        EditText editText3 = (EditText)findViewById(R.id.tel);
        String photo =mPhotoFile.getAbsolutePath().toString();


        String name = editText1.getText().toString();
        String add = editText2.getText().toString();
        String tel = editText3.getText().toString();

        long nOfRows = mDbHelper.insertUserByMethod(name,add,tel,photo);
        if (nOfRows >0)
            Toast.makeText(this,nOfRows+" Record Inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"No Record Inserted", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(),Registration.class);
        startActivity(intent);


    }












    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("asd",""+resultCode);


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (mPhotoFileName != null) {
                mPhotoFile = new File(Environment.getExternalStorageDirectory().getPath()+"/Pictures/", mPhotoFileName);
                ImageButton imageButton = (ImageButton) findViewById(R.id.imageBtn);
                Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getAbsolutePath());
                imageButton.setImageBitmap(bitmap);
                Log.i("asd","dfsdfs");

            } else
                Toast.makeText(getApplicationContext(), "mPhotoFile is null", Toast.LENGTH_SHORT).show();

        }
    }


    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }


    final int  REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA=1;

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA);

        }

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // permission was granted
            switch (requestCode) {
                case REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA:
                    break;
            }
        } else { // permission was denied
            Toast.makeText(getApplicationContext(),"접근 권한이 필요합니다",Toast.LENGTH_SHORT).show();
        }
    }

}
