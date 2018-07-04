package com.androidchatapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Chat extends Activity {
    Button sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;
    RecyclerView rvChatList;
    List<ChatMessage> chatMessages=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        sendButton = (Button)findViewById(R.id.btnSend);
        messageArea = (EditText)findViewById(R.id.etMessage);
        rvChatList = (RecyclerView) findViewById(R.id.rvChatList);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://friendly-chat-148df.firebaseio.com/messages/" + UserDetails.username + "_" + UserDetails.chatWith);
        reference2 = new Firebase("https://friendly-chat-148df.firebaseio.com/messages/" + UserDetails.chatWith + "_" + UserDetails.username);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();

                if(!messageText.equals("")){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", UserDetails.username);
                    map.put("date", String.valueOf(Calendar.getInstance().getTimeInMillis()));
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");
                }
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();
                String date = map.get("date").toString();

                if(userName.equals(UserDetails.username)){
                    addMessageBox(message,userName,date ,0);
                }
                else{
                    addMessageBox( message,userName ,date, 1);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void addMessageBox(String message,String user,String date, int type)
    {
        ChatMessage c=new ChatMessage(message,user,type);
        chatMessages.add(c);
        rvChatList.setLayoutManager(new LinearLayoutManager(this));
        rvChatList.setAdapter(new ChatMultiAdapter(chatMessages,this));
        rvChatList.scrollToPosition(chatMessages.size()-1);
    }




}