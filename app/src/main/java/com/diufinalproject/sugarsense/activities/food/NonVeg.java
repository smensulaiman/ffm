package com.diufinalproject.sugarsense.activities.food;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.diufinalproject.sugarsense.R;

public class NonVeg extends Fragment {


    public NonVeg() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_non_veg, container, false);
        final EditText editText = view.findViewById(R.id.edit);
        final TextView textView = view.findViewById(R.id.text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textView.setText(editText.getText().toString());
                if (editText.getText().toString().contains("এসে")){
                    textView.setText(textView.getText().toString().replaceAll("এসে","আইস্কে"));
                }if (editText.getText().toString().contains("বসে")){
                    textView.setText(textView.getText().toString().replaceAll("বসে","বইস্কে"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

}
