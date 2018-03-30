package com.myapp.darshan.sip;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by darshan on 29/3/18.
 */

public class DCforum extends AppCompatActivity{

    //private FirebaseListAdapter<ChatMessage> adapter;
    ArrayList<ChatMessage> arrayList;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("DCforum");
    final ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);

    final TextView messageText = (TextView)findViewById(R.id.message_text);
    final TextView messageUser = (TextView)findViewById(R.id.message_user);
    final TextView messageTime = (TextView)findViewById(R.id.message_time);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dc_forum);

        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);


                FirebaseDatabase.getInstance()
                        .getReference()
                        .child("DCForum")
                        .push()
                        .setValue(new ChatMessage(input.getText().toString(),
                                /*FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName()*/"Darshan now")
                        );

                // Clear the input
                input.setText("");
            }
        });



        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                arrayList.clear();
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()){
                    //String message = messageSnapshot.getValue(String.class);
                    ChatMessage model = messageSnapshot.getValue(ChatMessage.class);
                    messageText.setText(model.getMessageText());
                    messageUser.setText(model.getMessageUser());
                    messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
//                    if (model==null ){
//                        Toast.makeText(getApplicationContext(), "Null Message detected :", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
                    arrayList.add(model);

                }
                ArrayAdapter<ChatMessage> arrayAdapter= new ArrayAdapter<ChatMessage>(getApplicationContext(), R.layout.message,arrayList);
                listOfMessages.setAdapter(arrayAdapter);

//            protected void populateView(View v, ChatMessage model, int position) {
//                // Get references to the views of message.xml
//                TextView messageText = (TextView)v.findViewById(R.id.message_text);
//                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
//                TextView messageTime = (TextView)v.findViewById(R.id.message_time);
//
//                // Set their text
//                messageText.setText(model.getMessageText());
//                messageUser.setText(model.getMessageUser());
//
//                // Format the date before showing it
//                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
//                        model.getMessageTime()));
//            }
//
//        listOfMessages.setAdapter(adapter);
    }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}
