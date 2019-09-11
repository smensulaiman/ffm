package com.diufinalproject.sugarsense.dasshboard.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.activities.AboutActivity;
import com.diufinalproject.sugarsense.activities.CheckUp;
import com.diufinalproject.sugarsense.activities.SurveyWebActivity;
import com.diufinalproject.sugarsense.activities.bmi.BmiReport;
import com.diufinalproject.sugarsense.activities.chat.ChatList;
import com.diufinalproject.sugarsense.activities.expense.ExpenseActivity;
import com.diufinalproject.sugarsense.activities.expense.ExpenseList;
import com.diufinalproject.sugarsense.activities.food.FoodCalculation;
import com.diufinalproject.sugarsense.activities.map.GeocodeManager;
import com.diufinalproject.sugarsense.activities.map.MapsActivity;
import com.diufinalproject.sugarsense.activities.progress.ProgressActivity;
import com.diufinalproject.sugarsense.activities.read.TipsListActivity;
import com.diufinalproject.sugarsense.activities.tasks.TaskActivity;
import com.diufinalproject.sugarsense.gps.GpsFinder;
import com.diufinalproject.sugarsense.gps.GpsFinderCallback;
import com.diufinalproject.sugarsense.loginsignup.LoginAndSignup;
import com.diufinalproject.sugarsense.utils.DatasaveManager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class Home extends Fragment implements View.OnClickListener {

    LinearLayout layout_1, layout_2, layout_3, layout_4, layout_5, layout_6, layout_7, layout_8, layout_9, layout_10, layoutTrackNow;
    TextView userName;
    CircleImageView imgUserProfile;
    CardView attendance, expense, task, message;
    TextView status, address;

    private static int PICK_IMAGE_REQUEST = 700;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_type2, container, false);

        createView(view);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", null);

        userName = view.findViewById(R.id.txtUserName);
        //userName.setText(DatasaveManager.name);

//        if (!DatasaveManager.uri.equals("")) {
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), Uri.parse(DatasaveManager.uri));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            imgUserProfile.setImageBitmap(bitmap);
//        }
        imgUserProfile = view.findViewById(R.id.imgUserProfile);
        imgUserProfile.setOnClickListener(this);

        setClickListener();
        return view;
    }

    private void createView(View view) {
        attendance = view.findViewById(R.id.card_ck_bmi);
        status = view.findViewById(R.id.txtUserStatus);
        address = view.findViewById(R.id.txtUserAddress);
        expense = view.findViewById(R.id.ccal);
        task = view.findViewById(R.id.video);
        message = view.findViewById(R.id.services);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.layout_1:
                startActivity(new Intent(getContext(), TipsListActivity.class));
                break;
            case R.id.layout_2:
                startActivity(new Intent(getContext(), SurveyWebActivity.class));
                break;
            case R.id.layout_3:
                startActivity(new Intent(getContext(), BmiReport.class));
                break;
            case R.id.layout_4:
                startActivity(new Intent(getContext(), ChatList.class));
                break;
            case R.id.layout_5:
                startActivity(new Intent(getContext(), TaskActivity.class));
                break;
            case R.id.layout_6:
                startActivity(new Intent(getContext(), ProgressActivity.class));
                break;
            case R.id.layout_7:
                startActivity(new Intent(getContext(), FoodCalculation.class));
                break;
            case R.id.layout_8:
                startActivity(new Intent(getContext(), CheckUp.class));
                break;
            case R.id.layout_9:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
            case R.id.layout_10:
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("userlogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usersession", "logout");
                editor.commit();
                startActivity(new Intent(getContext(), LoginAndSignup.class));
                getActivity().finish();
                break;
            case R.id.layoutTrackNow:
                startActivity(new Intent(getContext(), MapsActivity.class));
                break;
            case R.id.imgUserProfile:
                chooseImage();
                break;
            case R.id.card_ck_bmi:
                makeAttendance();
                break;
            case R.id.ccal:
                startActivity(new Intent(getContext(), ExpenseList.class));
                break;
            case R.id.video:
                startActivity(new Intent(getContext(), TaskActivity.class));
                break;
            case R.id.services:
                startActivity(new Intent(getContext(), ChatList.class));
                break;
            default:
                break;
        }
    }

    private void makeAttendance() {

        new GpsFinder(getActivity()).getGps(new GpsFinderCallback() {
            @Override
            public void didGetGps(Location location, String gpsInfo) {

                String currentLocation = geocodeToAddress(getContext(), location.getLatitude() + "", location.getLongitude() + "");
                address.setText(currentLocation);
                String currentStatus = status.getText().toString();
                if (!currentStatus.contains("Entered")) {
                    status.setText("Entered");
                    status.setBackgroundColor(Color.parseColor("#37ae47"));
                } else {
                    status.setText("Exited");
                    status.setBackgroundColor(Color.parseColor("#F44336"));
                }

            }

            @Override
            public void didFailedGps(String error) {
                Toast.makeText(getContext(), "Location Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String geocodeToAddress(Context context, String latitude, String longitude) {

        if (GeocodeManager.isExist(context, latitude, longitude)) {
            return GeocodeManager.getGeoCode(context, latitude, longitude);
        }

        if (latitude == null) {
            return "No Address Found";
        }

        if (latitude.equals("null")) {
            return "No Address Found";
        }

        if (latitude.equals("0")) {
            return "No Address Found";
        }

        Geocoder geocoder;
        geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> singleAddress = geocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1);
            if (singleAddress != null && singleAddress.size() > 0) {
                String knownName = singleAddress.get(0).getFeatureName();
                String addressLine = singleAddress.get(0).getAddressLine(0);
                if (knownName == null) {
                    knownName = "";
                }
                String address = "";
                if (addressLine.startsWith(knownName)) {
                    address = addressLine;
                } else {
                    address = knownName + "," + addressLine;
                }
                if (!address.contains("Unnamed")) {
                    if (address.length() > 0) {
                        GeocodeManager.setGeoCode(context, latitude, longitude, address);
                        return address;
                    }
                }
            }

            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 5); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String bestAddress = getBestAddress(addresses);
            if (bestAddress.length() > 0) {
                GeocodeManager.setGeoCode(context, latitude, longitude, bestAddress);
            }

            return bestAddress;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No Address Found";
    }

    private static String getBestAddress(List<Address> addresses) {
        if (addresses == null) return "";
        if (addresses.size() == 0) return "";

        int size = addresses.size();
        if (size == 1) {
            String knownName = addresses.get(0).getFeatureName();
            String addressLine = addresses.get(0).getAddressLine(0);
            if (knownName == null) {
                knownName = "";
            }
            if (addressLine.startsWith(knownName)) {
                return addressLine;
            }
            return knownName + "," + addressLine;
        }

        String knownName = addresses.get(0).getFeatureName();
        String addressLine = addresses.get(0).getAddressLine(0);
        if (knownName == null) {
            knownName = "";
        }
        String fullAddress = knownName + "," + addressLine;
        if (fullAddress.startsWith(knownName)) {
            fullAddress = addressLine;
        }

        if (fullAddress.contains("Unnamed")) {
            knownName = addresses.get(1).getFeatureName();
            addressLine = addresses.get(1).getAddressLine(0);
            if (knownName == null) {
                knownName = "";
            }
            if (addressLine.startsWith(knownName)) {
                return addressLine;
            }
            return knownName + "," + addressLine;
        }
        return fullAddress;
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "SugarSense : Select Picture"), 1);
    }

    private void setClickListener() {
//        layout_1.setOnClickListener(this);
//        layout_2.setOnClickListener(this);
//        layout_3.setOnClickListener(this);
//        layout_4.setOnClickListener(this);
//        layout_5.setOnClickListener(this);
//        layout_6.setOnClickListener(this);
//        layout_7.setOnClickListener(this);
//        layout_8.setOnClickListener(this);
//        layout_9.setOnClickListener(this);
//        layout_10.setOnClickListener(this);
//        layoutTrackNow.setOnClickListener(this);
        attendance.setOnClickListener(this);
        expense.setOnClickListener(this);
        task.setOnClickListener(this);
        message.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            Uri uri = data.getData();
            DatasaveManager.uri = uri.toString();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                imgUserProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
