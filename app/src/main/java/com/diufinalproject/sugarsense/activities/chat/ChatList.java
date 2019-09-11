package com.diufinalproject.sugarsense.activities.chat;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.adapters.UserAdapter;
import com.diufinalproject.sugarsense.utils.DatasaveManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ChatList extends AppCompatActivity {

    private List<User> mUser;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        recyclerView = findViewById(R.id.recyclerViewUser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        final SweetAlertDialog pDialog = new SweetAlertDialog(ChatList.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        mUser = new ArrayList<>();
        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("user");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUser.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    if(!user.getName().equals(DatasaveManager.name)) {
                        mUser.add(user);
                    }
                }

                UserAdapter adapter = new UserAdapter(ChatList.this,mUser);
                recyclerView.setAdapter(adapter);
                if(pDialog.isShowing())
                    pDialog.dismissWithAnimation();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                if(pDialog.isShowing())
                    pDialog.dismissWithAnimation();
            }
        });
    }
}
