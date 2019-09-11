package com.diufinalproject.sugarsense.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.activities.food.RecycleAdapter;
import com.diufinalproject.sugarsense.adapters.DBAdapter;
import com.diufinalproject.sugarsense.models.DbItem;

import java.util.ArrayList;
import java.util.Date;

public class CheckUp extends AppCompatActivity {

    EditText editTextmgdl, editTextmmoll;

    MyTextWatcher t1, t2;
    DBAdapter adapter;

    Button submit;

    ArrayList<DbItem> items = new ArrayList<DbItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_up);

        submit = findViewById(R.id.btn_submit);
        editTextmgdl = findViewById(R.id.editTextmgdl);
        editTextmmoll = findViewById(R.id.editTextmmoll);

        t1 = new MyTextWatcher(editTextmgdl);
        t2 = new MyTextWatcher(editTextmmoll);

        editTextmgdl.addTextChangedListener(t1);
        editTextmmoll.addTextChangedListener(t2);

        RecyclerView recyclerView = findViewById(R.id.dblist);

        adapter = new DBAdapter(CheckUp.this,items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CheckUp.this));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextmgdl.getText().toString().equals("")){
                    items.add(new DbItem(editTextmgdl.getText().toString(),new Date().toString()));
                    adapter.setList(items);
                }
            }
        });
    }

    class MyTextWatcher implements TextWatcher {

        private EditText editText;

        public MyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            switch (editText.getId()) {
                case R.id.editTextmgdl:
                    if (!editText.getText().toString().trim().equals("")) {
                        float value = Float.parseFloat(editText.getText().toString().trim());
                        value = (float) value / 18;
                        editTextmmoll.removeTextChangedListener(t2);
                        editTextmmoll.setText(String.valueOf(value));
                    } else {
                        editTextmmoll.removeTextChangedListener(t2);
                        editTextmmoll.setText("");
                    }
                    editTextmmoll.addTextChangedListener(t2);
                    break;
                case R.id.editTextmmoll:
                    if (!editText.getText().toString().trim().equals("")) {
                        float value = Float.parseFloat(editText.getText().toString().trim());
                        value = (float) value * 18;
                        editTextmgdl.removeTextChangedListener(t1);
                        editTextmgdl.setText(String.valueOf(value));
                    } else {
                        editTextmgdl.removeTextChangedListener(t1);
                        editTextmgdl.setText("");
                    }
                    editTextmgdl.addTextChangedListener(t1);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
