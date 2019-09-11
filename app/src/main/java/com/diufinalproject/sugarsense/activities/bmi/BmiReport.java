package com.diufinalproject.sugarsense.activities.bmi;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.diufinalproject.sugarsense.R;
import com.github.capur16.digitspeedviewlib.DigitSpeedView;
import com.shinelw.library.ColorArcProgressBar;


public class BmiReport extends AppCompatActivity {

    CardView underw8, overw8, healthy, obese;
    TextView feet, w, bmitxt, status;
    private DigitSpeedView fmeter, imeter, weightmeter;
    private AppCompatSeekBar fseek, iseek, weightseek;
    private ColorArcProgressBar bar3;
    float weightKG, heightM, ft, in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //final DigitSpeedView digitSpeedView = (DigitSpeedView) findViewById(R.id.digit_speed_view);

        fmeter = (DigitSpeedView) findViewById(R.id.fmeter);
        imeter = (DigitSpeedView) findViewById(R.id.imeter);
        weightmeter = (DigitSpeedView) findViewById(R.id.weightmeter);
        fseek = (AppCompatSeekBar) findViewById(R.id.fseek);
        iseek = (AppCompatSeekBar) findViewById(R.id.iseek);
        weightseek = (AppCompatSeekBar) findViewById(R.id.weightseek);
        bmitxt = (TextView) findViewById(R.id.bmitxt);
        bar3 = (ColorArcProgressBar) findViewById(R.id.bar3);
        feet = (TextView) findViewById(R.id.feet);
        w = (TextView) findViewById(R.id.w);
        status = (TextView) findViewById(R.id.status);


        underw8 = (CardView) findViewById(R.id.underweight);
        overw8 = (CardView) findViewById(R.id.overweight);
        healthy = (CardView) findViewById(R.id.healthy);


        underw8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(DashboardActivity.this,HtmlView.class));
                Intent intent = new Intent(BmiReport.this, HtmlView.class);
                intent.putExtra("key", "w8gain7days");
                startActivity(intent);
            }
        });

        overw8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(DashboardActivity.this,HtmlView.class));
                Intent intent = new Intent(BmiReport.this, HtmlView.class);
                intent.putExtra("key", "w8loss7days");
                startActivity(intent);
            }
        });

        fseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                fmeter.updateSpeed(i);
                i *= 12;
                float fh = (float) i;
                ft = (float) (fh / 39.3701);
                heightM = calculateFtoC(ft, in);
                bmitxt.setText(String.valueOf(calculateBMI(heightM, weightKG)));

                if ((int) calculateBMI(heightM, weightKG) < 60) {
                    bar3.setCurrentValues(calculateBMI(heightM, weightKG));
                    //digitSpeedView.updateSpeed((int) calculateBMI(heightM, weightKG));
                } else {
                    //Toast.makeText(DashboardActivity.this, "MAX LENGTH REACHED", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        iseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                imeter.updateSpeed(i);
                float fh = (float) i;
                in = (float) (fh / 39.3701);
                heightM = calculateFtoC(ft, in);
                bmitxt.setText(String.valueOf(calculateBMI(heightM, weightKG)));
                if ((int) calculateBMI(heightM, weightKG) < 61) {
                    bar3.setCurrentValues(calculateBMI(heightM, weightKG));
                    //digitSpeedView.updateSpeed((int) calculateBMI(heightM, weightKG));
                } else {
                    //Toast.makeText(DashboardActivity.this, "MAX LENGTH REACHED", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        weightseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                weightmeter.updateSpeed(i);
                weightKG = (float) i;
                bmitxt.setText(String.valueOf(calculateBMI(heightM, weightKG)));
                if ((int) calculateBMI(heightM, weightKG) < 61) {
                    bar3.setCurrentValues(calculateBMI(heightM, weightKG));
                    //digitSpeedView.updateSpeed((int) calculateBMI(heightM, weightKG));
                } else {
                    //Toast.makeText(DashboardActivity.this, "MAX LENGTH REACHED", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public float calculateFtoC(float f, float i) {
        return f + i;
    }

    public float calculateBMI(float height, float weight) {
        feet.setText(String.valueOf(height));
        w.setText(String.valueOf(weight));
        int wgol = 0;

        float bmi = weight / (height * height);

        int ibmi = (int) bmi;
        if (ibmi <= 19) {
            moonMoonDisabler('u');
            wgol = (int) (((weight / ibmi) * 22) - weight);
            status.setText("you are underweight\nyou have to increase " + wgol + " kg");
            status.setTextColor(Color.parseColor("#FF495E"));
        } else if (ibmi > 19 && ibmi <= 25) {
            moonMoonDisabler('h');
            status.setText("you are Healthy");
            status.setTextColor(Color.parseColor("#439700"));
        } else if (ibmi > 25) {
            moonMoonDisabler('o');
            wgol = (int) (weight - ((weight / ibmi) * 22));
            status.setText("you are overweight\nyou have to decrease " + wgol + " kg");
            status.setTextColor(Color.parseColor("#FF495E"));
        }
        return bmi;
    }

    public void moonMoonDisabler(char c) {

        switch (c) {
            case 'u':
                underw8.setEnabled(true);
                overw8.setEnabled(false);
                healthy.setEnabled(false);
                break;
            case 'h':
                underw8.setEnabled(false);
                overw8.setEnabled(false);
                healthy.setEnabled(true);
                break;
            case 'o':
                underw8.setEnabled(false);
                overw8.setEnabled(true);
                healthy.setEnabled(false);
                break;
            case 'b':
                underw8.setEnabled(false);
                overw8.setEnabled(true);
                healthy.setEnabled(false);
                break;
        }

    }
}
