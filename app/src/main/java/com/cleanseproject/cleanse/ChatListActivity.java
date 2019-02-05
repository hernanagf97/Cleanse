package com.cleanseproject.cleanse;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.cleanseproject.cleanse.dataClasses.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;

    private ListView chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        chatList = findViewById(R.id.chat_list);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    private void getUserChats() {
        DatabaseReference userChats = firebaseDatabase.getReference("userChats").child(firebaseUser.getUid());
        userChats.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<User> users = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        users.add(dataSnapshot.getValue(User.class));
                    }
                }
                populateList(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void populateList(ArrayList<User> users) {

    }

}