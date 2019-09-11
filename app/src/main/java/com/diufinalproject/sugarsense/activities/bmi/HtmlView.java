package com.diufinalproject.sugarsense.activities.bmi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.diufinalproject.sugarsense.AlarmAndNotification.AlertReceiver;
import com.diufinalproject.sugarsense.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Calendar;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class HtmlView extends AppCompatActivity {

    TextView text;
    DatabaseReference reff;

    AlarmListAdapter adapter1, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7;
    ListView listView1, listView2, listView3, listView4, listView5, listView6, listView7;

    Button setAlarm;

    ArrayList<AlarmModel> list1 = new ArrayList<>();
    ArrayList<AlarmModel> list2 = new ArrayList<>();
    ArrayList<AlarmModel> list3 = new ArrayList<>();
    ArrayList<AlarmModel> list4 = new ArrayList<>();
    ArrayList<AlarmModel> list5 = new ArrayList<>();
    ArrayList<AlarmModel> list6 = new ArrayList<>();
    ArrayList<AlarmModel> list7 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_view);

        text = (TextView) findViewById(R.id.text);

        String key = getIntent().getStringExtra("key");

        reff = FirebaseDatabase.getInstance().getReference().child(key);
        setAlarm = (Button)findViewById(R.id.setAlarm);

        listView1 = findViewById(R.id.v2list1);
        listView2 = findViewById(R.id.v2list2);
        listView3 = findViewById(R.id.v2list3);
        listView4 = findViewById(R.id.v2list4);
        listView5 = findViewById(R.id.v2list5);
        listView6 = (ListView) findViewById(R.id.v2list6);
        listView7 = (ListView) findViewById(R.id.v2list7);

        adapter1 = new AlarmListAdapter(this, list1);
        adapter2 = new AlarmListAdapter(this, list2);
        adapter3 = new AlarmListAdapter(this, list3);
        adapter4 = new AlarmListAdapter(this, list4);
        adapter5 = new AlarmListAdapter(this, list5);
        adapter6 = new AlarmListAdapter(this, list6);
        adapter7 = new AlarmListAdapter(this, list7);

        final SweetAlertDialog pDialog = new SweetAlertDialog(HtmlView.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int day = 1;

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                    String oldtemp = childSnapshot.getKey();
                    //text.append(oldtemp+"\n");
                    switch (day) {

                        case 1:
                            for (DataSnapshot childSnapshot1 : childSnapshot.getChildren()) {
                                String temp = childSnapshot1.getKey();
                                //data.add(temp);
                                //text.append(temp+" : "+childSnapshot.child(temp).getValue().toString()+"\n");
                                String desc = childSnapshot.child(temp).getValue().toString();
                                if (temp != null && desc != null) {
                                    list1.add(new AlarmModel(temp, desc));
                                    System.out.println(temp + " " + desc);
                                }

                            }
                            break;
                        case 2:
                            for (DataSnapshot childSnapshot1 : childSnapshot.getChildren()) {
                                String temp = childSnapshot1.getKey();
                                //data.add(temp);
                                //text.append(temp+" : "+childSnapshot.child(temp).getValue().toString()+"\n");
                                String desc = childSnapshot.child(temp).getValue().toString();
                                if (temp != null && desc != null) {
                                    list2.add(new AlarmModel(temp, desc));
                                    System.out.println(temp + " " + desc);
                                }

                            }
                            break;
                        case 3:
                            for (DataSnapshot childSnapshot1 : childSnapshot.getChildren()) {
                                String temp = childSnapshot1.getKey();
                                //data.add(temp);
                                //text.append(temp+" : "+childSnapshot.child(temp).getValue().toString()+"\n");
                                String desc = childSnapshot.child(temp).getValue().toString();
                                if (temp != null && desc != null) {
                                    list3.add(new AlarmModel(temp, desc));
                                    System.out.println(temp + " " + desc);
                                }

                            }
                            break;
                        case 4:
                            for (DataSnapshot childSnapshot1 : childSnapshot.getChildren()) {
                                String temp = childSnapshot1.getKey();
                                //data.add(temp);
                                //text.append(temp+" : "+childSnapshot.child(temp).getValue().toString()+"\n");
                                String desc = childSnapshot.child(temp).getValue().toString();
                                if (temp != null && desc != null) {
                                    list4.add(new AlarmModel(temp, desc));
                                    System.out.println(temp + " " + desc);
                                }

                            }
                            break;
                        case 5:
                            for (DataSnapshot childSnapshot1 : childSnapshot.getChildren()) {
                                String temp = childSnapshot1.getKey();
                                //data.add(temp);
                                //text.append(temp+" : "+childSnapshot.child(temp).getValue().toString()+"\n");
                                String desc = childSnapshot.child(temp).getValue().toString();
                                if (temp != null && desc != null) {
                                    list5.add(new AlarmModel(temp, desc));
                                    System.out.println(temp + " " + desc);
                                }

                            }
                            break;
                        case 6:
                            for (DataSnapshot childSnapshot1 : childSnapshot.getChildren()) {
                                String temp = childSnapshot1.getKey();
                                //data.add(temp);
                                //text.append(temp+" : "+childSnapshot.child(temp).getValue().toString()+"\n");
                                String desc = childSnapshot.child(temp).getValue().toString();
                                if (temp != null && desc != null) {
                                    list6.add(new AlarmModel(temp, desc));
                                    System.out.println(temp + " " + desc);
                                }

                            }
                            break;
                        case 7:
                            for (DataSnapshot childSnapshot1 : childSnapshot.getChildren()) {
                                String temp = childSnapshot1.getKey();
                                //data.add(temp);
                                //text.append(temp+" : "+childSnapshot.child(temp).getValue().toString()+"\n");
                                String desc = childSnapshot.child(temp).getValue().toString();
                                if (temp != null && desc != null) {
                                    list7.add(new AlarmModel(temp, desc));
                                    System.out.println(temp + " " + desc);
                                }

                            }
                            break;

                        default:
                            break;

                    }

                    day++;

                    adapter1.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                    adapter3.notifyDataSetChanged();
                    adapter4.notifyDataSetChanged();
                    adapter5.notifyDataSetChanged();
                    adapter6.notifyDataSetChanged();
                    adapter7.notifyDataSetChanged();


                    listView1.setAdapter(adapter1);
                    listView2.setAdapter(adapter2);
                    listView3.setAdapter(adapter3);
                    listView4.setAdapter(adapter4);
                    listView5.setAdapter(adapter5);
                    listView6.setAdapter(adapter6);
                    listView7.setAdapter(adapter7);


                    ListUtils.setDynamicHeight(listView1);
                    ListUtils.setDynamicHeight(listView2);
                    ListUtils.setDynamicHeight(listView3);
                    ListUtils.setDynamicHeight(listView4);
                    ListUtils.setDynamicHeight(listView5);
                    ListUtils.setDynamicHeight(listView6);
                    ListUtils.setDynamicHeight(listView7);


                    pDialog.dismissWithAnimation();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                for(int i=1;i<=7;i++){
                    //startAlarm(i,time+=50000);
                    switch (i){
                        case 1:
                            int alert1 = 1;
                            for(AlarmModel model:list1){

                                int hour,min;

                                String timeWithRegex = model.getTime();
                                String timeWitRegexToCharArray [] = timeWithRegex.split("-");
                                if(timeWitRegexToCharArray[0].toLowerCase().contains("am")){
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("am","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0]);
                                        min = Integer.parseInt(hourAndMin[1]);

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].replace(" ",""));
                                        min = 00;
                                    }

                                }else{
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("pm","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0].replace(" ",""));
                                        min = Integer.parseInt(hourAndMin[1].replace(" ",""));

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].replace(" ",""))+12;
                                        min = 00;
                                    }

                                }
                                String desc = model.getDesc();
                                calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                //Toast.makeText(HtmlView.this,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)),Toast.LENGTH_SHORT).show();
                                calendar.set(Calendar.MINUTE,min);
                                long time = calendar.getTimeInMillis();

                                try{
                                    startAlarm(alert1++,time,timeWithRegex,desc);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            break;



                        case 2:
                            int alert2 = 1;
                            for(AlarmModel model:list2){

                                int hour,min;

                                String timeWithRegex = model.getTime();
                                String timeWitRegexToCharArray [] = timeWithRegex.split("-");
                                if(timeWitRegexToCharArray[0].toLowerCase().contains("am")){
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("am","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0]);
                                        min = Integer.parseInt(hourAndMin[1]);

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim());
                                        min = 00;
                                    }

                                    //Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }else{
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("pm","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0].trim());
                                        min = Integer.parseInt(hourAndMin[1].trim());

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim())+12;
                                        min = 00;
                                    }

                                    // Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }
                                String desc = model.getDesc();

                                //Toast.makeText(HtmlView.this,"hour "+hour+" min "+min,Toast.LENGTH_SHORT).show();


                                calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1);
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                //Toast.makeText(HtmlView.this,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)),Toast.LENGTH_SHORT).show();
                                calendar.set(Calendar.MINUTE,min);
                                long time = calendar.getTimeInMillis();

                                try{
                                    startAlarm(alert2++,time,timeWithRegex,desc);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            break;


                        case 3:
                            int alert3 = 1;
                            for(AlarmModel model:list3){

                                int hour,min;

                                String timeWithRegex = model.getTime();
                                String timeWitRegexToCharArray [] = timeWithRegex.split("-");
                                if(timeWitRegexToCharArray[0].toLowerCase().contains("am")){
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("am","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0].trim());
                                        min = Integer.parseInt(hourAndMin[1].trim());

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim());
                                        min = 00;
                                    }

                                    //Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }else{
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("pm","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0].trim());
                                        min = Integer.parseInt(hourAndMin[1].trim());

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim())+12;
                                        min = 00;
                                    }

                                    // Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }
                                String desc = model.getDesc();

                                // Toast.makeText(HtmlView.this,"hour "+hour+" min "+min,Toast.LENGTH_SHORT).show();


                                calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+2);
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                //Toast.makeText(HtmlView.this,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)),Toast.LENGTH_SHORT).show();
                                calendar.set(Calendar.MINUTE,min);
                                long time = calendar.getTimeInMillis();

                                try{
                                    startAlarm(alert3++,time,timeWithRegex,desc);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            break;

                        case 4:
                            int alert4 = 1;
                            for(AlarmModel model:list4){

                                int hour,min;

                                String timeWithRegex = model.getTime();
                                String timeWitRegexToCharArray [] = timeWithRegex.split("-");
                                timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].trim();
                                if(timeWitRegexToCharArray[0].toLowerCase().contains("am")){
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("am","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0]);
                                        min = Integer.parseInt(hourAndMin[1]);

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim());
                                        min = 00;
                                    }

                                    //Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }else{
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("pm","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0]);
                                        min = Integer.parseInt(hourAndMin[1].trim());

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim())+12;
                                        min = 00;
                                    }

                                    // Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }
                                String desc = model.getDesc();

                                //Toast.makeText(HtmlView.this,"hour "+hour+" min "+min,Toast.LENGTH_SHORT).show();


                                calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+3);
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                //Toast.makeText(HtmlView.this,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)),Toast.LENGTH_SHORT).show();
                                calendar.set(Calendar.MINUTE,min);
                                long time = calendar.getTimeInMillis();

                                try{
                                    startAlarm(alert4++,time,timeWithRegex,desc);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            break;

                        case 5:
                            int alert5 = 1;
                            for(AlarmModel model:list5){

                                int hour,min;

                                String timeWithRegex = model.getTime();
                                String timeWitRegexToCharArray [] = timeWithRegex.split("-");
                                timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].trim();
                                if(timeWitRegexToCharArray[0].toLowerCase().contains("am")){
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("am","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0].trim());
                                        min = Integer.parseInt(hourAndMin[1].trim());

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim());
                                        min = 00;
                                    }

                                    //Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }else{
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("pm","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0].trim());
                                        min = Integer.parseInt(hourAndMin[1].trim());

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim())+12;
                                        min = 00;
                                    }

                                    // Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }
                                String desc = model.getDesc();

                                //Toast.makeText(HtmlView.this,"hour "+hour+" min "+min,Toast.LENGTH_SHORT).show();


                                calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+4);
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                //Toast.makeText(HtmlView.this,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)),Toast.LENGTH_SHORT).show();
                                calendar.set(Calendar.MINUTE,min);
                                long time = calendar.getTimeInMillis();

                                try{
                                    startAlarm(alert5++,time,timeWithRegex,desc);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            break;

                        case 6:
                            int alert6 = 1;
                            for(AlarmModel model:list6){

                                int hour,min;

                                String timeWithRegex = model.getTime();
                                String timeWitRegexToCharArray [] = timeWithRegex.split("-");
                                timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].trim();
                                if(timeWitRegexToCharArray[0].toLowerCase().contains("am")){
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("am","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0].trim());
                                        min = Integer.parseInt(hourAndMin[1].trim());

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim());
                                        min = 00;
                                    }

                                    //Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }else{
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("pm","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0].trim());
                                        min = Integer.parseInt(hourAndMin[1].trim());

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim())+12;
                                        min = 00;
                                    }

                                    // Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }
                                String desc = model.getDesc();

                                //Toast.makeText(HtmlView.this,"hour "+hour+" min "+min,Toast.LENGTH_SHORT).show();


                                calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+5);
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                //Toast.makeText(HtmlView.this,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)),Toast.LENGTH_SHORT).show();
                                calendar.set(Calendar.MINUTE,min);
                                long time = calendar.getTimeInMillis();

                                try{
                                    startAlarm(alert6++,time,timeWithRegex,desc);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            break;

                        case 7:
                            int alert7 = 1;
                            for(AlarmModel model:list7){

                                int hour,min;

                                String timeWithRegex = model.getTime();
                                String timeWitRegexToCharArray [] = timeWithRegex.split("-");
                                timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].trim();
                                if(timeWitRegexToCharArray[0].toLowerCase().contains("am")){
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("am","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0].trim());
                                        min = Integer.parseInt(hourAndMin[1].trim());

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim());
                                        min = 00;
                                    }

                                    //Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }else{
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("pm","");
                                    timeWitRegexToCharArray[0] = timeWitRegexToCharArray[0].replaceAll("\\(","");

                                    if(timeWitRegexToCharArray[0].contains(":")){

                                        String hourAndMin [] = timeWitRegexToCharArray[0].split(":");
                                        hour = Integer.parseInt(hourAndMin[0].trim());
                                        min = Integer.parseInt(hourAndMin[1].trim());

                                    }else{
                                        hour = Integer.parseInt(timeWitRegexToCharArray[0].trim())+12;
                                        min = 00;
                                    }

                                    // Toast.makeText(HtmlView.this,"time is "+timeWitRegexToCharArray[0],Toast.LENGTH_SHORT).show();
                                }
                                String desc = model.getDesc();
                                calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+6);
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                //Toast.makeText(HtmlView.this,String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)),Toast.LENGTH_SHORT).show();
                                calendar.set(Calendar.MINUTE,min);
                                long time = calendar.getTimeInMillis();

                                try{
                                    startAlarm(alert7++,time,timeWithRegex,desc);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            break;

                        default:
                            break;
                    }
                }
                Toast.makeText(HtmlView.this,"alarm set successfully for 7 days",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void startAlarm(int id,long alertTime,String atime,String amsg) {
        //Toast.makeText(this,"Alarm Started id "+id+" time "+alertTime,Toast.LENGTH_SHORT).show();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("time",atime);
        intent.putExtra("msg",amsg);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, intent,0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alertTime, pendingIntent);
        }else{
            Toast.makeText(this,"Not Supported",Toast.LENGTH_SHORT).show();
        }
    }


    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }

}
