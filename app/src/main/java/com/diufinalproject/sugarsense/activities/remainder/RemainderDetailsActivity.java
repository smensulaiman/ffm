package com.diufinalproject.sugarsense.activities.remainder;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.adapters.RemainderDetailsAdapter;
import com.diufinalproject.sugarsense.adapters.RemainderDetailsAdapterListener;
import com.diufinalproject.sugarsense.models.ModelRemainder;

import java.util.ArrayList;

public class RemainderDetailsActivity extends AppCompatActivity {

    private RemainderDetailsAdapter adapter;
    private ArrayList<ModelRemainder> arrModels = new ArrayList<>();
    RecyclerView recyclerViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder_details);

        recyclerViewList = findViewById(R.id.recyclerViewList);

        recyclerViewList.setLayoutManager(new GridLayoutManager(RemainderDetailsActivity.this, 1));
        recyclerViewList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                outRect.set(2, 2, 2, 2);
            }
        });

        recyclerViewList.setPadding(2, 2, 2, 2);
        adapter = new RemainderDetailsAdapter(RemainderDetailsActivity.this, new ArrayList<ModelRemainder>());
        recyclerViewList.setAdapter(adapter);

        adapter.setRemainderDetailsAdapterListener(new RemainderDetailsAdapterListener() {
            @Override
            public void didPressSubmit(String suggestionId, String amount) {

            }

            @Override
            public void onItemClick(int position,String id) {

            }
        });
    }
}
