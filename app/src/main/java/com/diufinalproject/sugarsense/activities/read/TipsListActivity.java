package com.diufinalproject.sugarsense.activities.read;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.diufinalproject.sugarsense.MyDatabase;
import com.diufinalproject.sugarsense.R;

public class TipsListActivity extends AppCompatActivity {
    ListView list;
    ListAdapter mAdapter;

    MyDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_list);

        list = findViewById(R.id.list);

        db = new MyDatabase(this);
        db.forceDatabaseReload(this);

        mAdapter = new ListAdapter(this,db.getAllTips());
        list.setAdapter(mAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.text_view_item_name);
                Intent intent = new Intent(TipsListActivity.this,ReadTips.class);
                intent.putExtra("POSITION",position);
                intent.putExtra("HEAD",textView.getText().toString());
                startActivity(intent);

            }
        });
    }
}
