package com.diufinalproject.sugarsense.activities.expense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.diufinalproject.sugarsense.R;

import java.util.Date;

public class ExpenseActivity extends AppCompatActivity implements TextWatcher {

    EditText txtScheduleVisit, txtVisitingArea, txtPurpose, txtConveyance, txtDailyAllowance, txtHotelRent, txtFood, txtPhotostat, txtFax, txtOthers;
    Button btnSubmit;
    TextView txtTotalTaka;
    float totalTaka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        txtScheduleVisit = findViewById(R.id.txtScheduleVisit);
        txtVisitingArea = findViewById(R.id.txtVisitingArea);
        txtPurpose = findViewById(R.id.txtPurpose);
        txtConveyance = findViewById(R.id.txtConveyance);
        txtDailyAllowance = findViewById(R.id.txtDailyAllowance);
        txtHotelRent = findViewById(R.id.txtHotelRent);
        txtFood = findViewById(R.id.txtFood);
        txtPhotostat = findViewById(R.id.txtPhotostat);
        txtFax = findViewById(R.id.txtFax);
        txtOthers = findViewById(R.id.txtOthers);
        txtTotalTaka = findViewById(R.id.txtTotalTaka);
        txtTotalTaka.setText("0 TAKA");
        totalTaka = 0;

        txtConveyance.addTextChangedListener(this);
        txtDailyAllowance.addTextChangedListener(this);
        txtHotelRent.addTextChangedListener(this);
        txtFood.addTextChangedListener(this);
        txtPhotostat.addTextChangedListener(this);
        txtFax.addTextChangedListener(this);
        txtOthers.addTextChangedListener(this);


        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpenseSession.scheduleVisit = txtScheduleVisit.getText().toString();
                ExpenseSession.companyName = txtVisitingArea.getText().toString();
                ExpenseSession.purpose = txtPurpose.getText().toString();
                ExpenseSession.conveyance = txtConveyance.getText().toString();
                ExpenseSession.dailyAllowance = txtDailyAllowance.getText().toString();
                ExpenseSession.hotelRent = txtHotelRent.getText().toString();
                ExpenseSession.food = txtFood.getText().toString();
                ExpenseSession.photostat = txtPhotostat.getText().toString();
                ExpenseSession.fax = txtFax.getText().toString();
                ExpenseSession.others = txtOthers.getText().toString();
                ExpenseSession.dateTime = new Date().toString();
                ExpenseSession.amount = txtTotalTaka.getText().toString();
                ExpenseSession.isEmpty = false;
                ExpenseSession.onBack = true;
                onBackPressed();
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        totalTaka = (txtConveyance.getText().toString().length() != 0 ? Float.parseFloat(txtConveyance.getText().toString()) : 0) +
                (txtDailyAllowance.getText().toString().length() != 0 ? Integer.parseInt(txtDailyAllowance.getText().toString()) : 0) +
                (txtHotelRent.getText().toString().length() != 0 ? Integer.parseInt(txtHotelRent.getText().toString()) : 0) +
                (txtFood.getText().toString().length() != 0 ? Integer.parseInt(txtFood.getText().toString()) : 0) +
                (txtPhotostat.getText().toString().length() != 0 ? Integer.parseInt(txtPhotostat.getText().toString()) : 0) +
                (txtFax.getText().toString().length() != 0 ? Integer.parseInt(txtFax.getText().toString()) : 0) +
                (txtOthers.getText().toString().length() != 0 ? Integer.parseInt(txtOthers.getText().toString()) : 0);

        txtTotalTaka.setText(totalTaka + " TAKA");

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
