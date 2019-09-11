package com.diufinalproject.sugarsense.activities.food;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.diufinalproject.sugarsense.R;

import java.io.ByteArrayOutputStream;

public class FoodCalculation extends AppCompatActivity {

    private ViewPager mViewpager;
    String filePath;
    Dialog dialog;

    private static final int PICK_PHOTO = 1958;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_calculation);

        mViewpager = findViewById(R.id.viewPager);

        verifyStoragePermissions(this);
        FoodPagerAdapter adapter = new FoodPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Vegeterian(), "veg");
        adapter.addFragment(new NonVeg(), "non-veg");

        mViewpager.setAdapter(adapter);

        final TabLayout layout = (TabLayout) findViewById(R.id.tabLayout);
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    layout.setTabTextColors(Color.BLACK, Color.parseColor("#008577"));
                } else if (tab.getPosition() == 1) {
                    layout.setTabTextColors(Color.BLACK, Color.parseColor("#ed5a5f"));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        layout.setupWithViewPager(mViewpager);

    }

    public void openCamera(View view) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Uri tempUri = getImageUri(this.getApplicationContext(), imageBitmap);
            filePath = getPath(tempUri);
//            NetworkCall.fileUpload(filePath, new ImageSenderInfo("obj", 22));
//            // imageView.setImageBitmap(imageBitmap);
//
//            dialog = new Dialog(getApplicationContext());
//
//            dialog.setContentView(R.layout.image_preview);
//
//            ImageView view = dialog.findViewById(R.id.image_view);
//
//            view.setImageBitmap(imageBitmap);
//
//            dialog.show();

        }

    }


    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    void closeDialog(){
        dialog.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
       // EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        //EventBus.getDefault().unregister(this);
        super.onStop();
    }

    //@Subscribe(threadMode = ThreadMode.MAIN)
    /*public void onEvent(EventModel event) throws ClassNotFoundException {
        if (event.isTagMatchWith("response")) {
            String responseMessage = "Response from Server:\n" + event.getMessage();
            //responseTextView.setText(responseMessage);
            Toast.makeText(this, responseMessage, Toast.LENGTH_SHORT).show();
        }
    }
    */

}

