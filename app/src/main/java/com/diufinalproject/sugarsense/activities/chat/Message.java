package com.diufinalproject.sugarsense.activities.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.adapters.MessageAdapter;
import com.diufinalproject.sugarsense.models.Chats;
import com.diufinalproject.sugarsense.utils.DatasaveManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Message extends AppCompatActivity {

    private TextView textView;
    private ImageButton btn_send;
    private EditText txt_send;
    private DatabaseReference ref;
    List<Chats> mchats;
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    String currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.chats);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        textView = (TextView) findViewById(R.id.name);
        textView.setText(getIntent().getStringExtra("name"));
        btn_send = (ImageButton) findViewById(R.id.btn_send);
        txt_send = (EditText) findViewById(R.id.txt_send);

        if(!DatasaveManager.name.equals(null)){
            currentUser = DatasaveManager.name;
        }else{
            currentUser="admin";
        }

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txt_send.getText().toString().equals("")) {
                    sendMessage(currentUser, getIntent().getStringExtra("name"), txt_send.getText().toString());
                    txt_send.setText("");
                } else {
                    Toast.makeText(Message.this, "message can not be empty", Toast.LENGTH_SHORT).show();

                }
            }
        });
        readMessage(currentUser, getIntent().getStringExtra("name"));
    }

    public void sendMessage(String sender, String receiver, String message) {

        ref = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);

        ref.child("chats").push().setValue(hashMap);
        readMessage(sender,receiver);
    }
    public void readMessage(final String sender, final String receiver) {
        mchats = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("chats");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mchats.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chats chats = snapshot.getValue(Chats.class);
                    if((chats.getSender().equals(sender)&&chats.getReceiver().equals(receiver))||(chats.getSender().equals(receiver)&&chats.getReceiver().equals(sender))){
                        mchats.add(chats);
                    }
                }

                messageAdapter = new MessageAdapter(Message.this,mchats);
                recyclerView.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
