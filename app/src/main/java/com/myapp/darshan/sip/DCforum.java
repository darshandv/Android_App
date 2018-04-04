package com.myapp.darshan.sip;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by darshan on 29/3/18.
 */

public class DCforum extends AppCompatActivity{

    public FirebaseListAdapter<ChatMessage> adapter;
    ArrayList<ChatMessage> listitems =new ArrayList<>();
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("DCforum");
    ListView listOfMessages;

    TextView messageText;
    TextView messageUser;
    TextView messageTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dc_forum);

        //listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        /*messageText = (TextView)findViewById(R.id.message_text);
        messageUser = (TextView)findViewById(R.id.message_user);
        messageTime = (TextView)findViewById(R.id.message_time);*/
        listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);


                FirebaseDatabase.getInstance().getReference().child("DCforum").push()
                        .setValue(new ChatMessage(input.getText().toString()
                                /*FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName()*/, "Darshan")
                        );

                // Clear the input
                input.setText("");
            }
        });



        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                //arrayList.clear();
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    //String message = messageSnapshot.getValue(String.class);
                    ChatMessage model = messageSnapshot.getValue(ChatMessage.class);
                    Log.e("Check", model.getMessageText());
                    /*messageText.setText(model.getMessageText());
                    messageUser.setText(model.getMessageUser());
                    messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                            model.getMessageTime()));*/
//                    if (model==null ){
//                        Toast.makeText(getApplicationContext(), "Null Message detected :", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
                    listitems.add(model);

                }
                Adapter arrayAdapter=new Adapter(getApplicationContext(),listitems);
                listOfMessages.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                listOfMessages = (ListView)findViewById(R.id.list_of_messages);
////                Query query = FirebaseDatabase.getInstance().getReference().child("DCForum");
////
////                FirebaseListOptions<ChatMessage> options =
////                        new FirebaseListOptions.Builder<ChatMessage>()
////                                .setQuery(query, ChatMessage.class)
////                                .setLayout(android.R.layout.simple_list_item_1)
////                                .build();
////                adapter = new FirebaseListAdapter<ChatMessage>(options) {
////                    @Override
////                    protected void populateView(View v, ChatMessage model, int position) {
////                        TextView messageText = (TextView)v.findViewById(R.id.message_text);
////                        TextView messageUser = (TextView)v.findViewById(R.id.message_user);
////                        TextView messageTime = (TextView)v.findViewById(R.id.message_time);
////
////                        // Set their text
////                        messageText.setText(model.getMessageText());
////                        messageUser.setText(model.getMessageUser());
////                        Log.e("Received", model.getMessageText());
////
////                        // Format the date before showing it
////                        messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
////                                model.getMessageTime()));
////                    }
////                };
////                listOfMessages.setAdapter(adapter);
//                listitems.clear();
//                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()){
//                    String message = messageSnapshot.getValue(String.class);
//                    if (message==null ){
//                        Toast.makeText(getApplicationContext(), "Null Message detected :", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    Log.d("Check",message);
//                    listitems.add(message.toString());
//
//
//                }
//                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listitems);
//                listOfMessages.setAdapter(arrayAdapter);
//                arrayAdapter.notifyDataSetChanged();
//                Toast.makeText(getApplicationContext(), "Message detected : ", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), "Message cancelled", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
