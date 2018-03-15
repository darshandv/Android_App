package com.myapp.darshan.sip;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import com.myapp.darshan.sip.MessageData;
import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class News extends Fragment {


    public News() {
        // Required empty public constructor
    }

//    public class MessageData{
//        public String body;
//
//        public MessageData(){
//
//        }
//
//        public MessageData(String body){
//            this.body=body;
//        }
//
//        public Map<String, Object> toMap() {
//            HashMap<String, Object> result = new HashMap<>();
//            result.put("body", body);
//
//            return result;
//        }
//    }

    EditText text;
    ScrollView scroll;
    String admin = "admin";
    Button sendButton;
    static int id=1;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("NEWS");
        text = (EditText)view.findViewById(R.id.editMessage);
        sendButton =(Button) view.findViewById(R.id.buttonSend);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = text.getText().toString();
                if(!data.equals("")) {
                    MessageData datamap = new MessageData(data);
                    mDatabase.push().setValue(datamap.toMap());
                    if (text.length() > 0) {
                        text.getText().clear();
                    }
                }
            }
        });

        mDatabase.child("NEWS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                MessageData message = dataSnapshot.getValue(MessageData.class);

                if (message==null || message.body.equals("")){
                    Log.e("Null", "Null");
                    return;
                }
                Log.d("Message", "Message Received");
                //LinearLayout layout = (LinearLayout) findViewById(R.id.info);
                LinearLayout layout = new LinearLayout(getActivity());
                layout.setBackgroundColor(getResources().getColor(R.color.viewBackground));

                TextView receivedMessage = new TextView(getActivity());
                receivedMessage.setText( message.body);
                receivedMessage.setId(id++);
                receivedMessage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                receivedMessage.setTextColor(getResources().getColor(R.color.colorBlack));

                ((LinearLayout) layout).addView(receivedMessage);
//                List notes = new ArrayList<>();
//                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
//                    Note note = noteDataSnapshot.getValue(Note.class);
//                    notes.add(note);
//                }
//                adapter.updateList(notes);
                Toast.makeText(getContext(), "Message detected :"+message.body, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Message cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



//    @IgnoreExtraProperties
//    public class User{
//        public String username;
//
//
//        public User(){
//        }
//        public User(String username){
//           this.username=username;
//        }
//    }
//
//    public class Post{
//        public String author;
//        public String body;
//        public int starCount = 0;
//        public Map<String, Boolean> stars = new HashMap<>();
//        public Post(){
//
//        }
//        public Post(String author, String body){
//            this.author=author;
//            this.body=body;
//        }
//        @Exclude
//        public Map<String, Object> toMap() {
//            HashMap<String, Object> result = new HashMap<>();
//            result.put("author", author);
//            result.put("body", body);
//
//            return result;
//        }
//
//    }
//
//
//
//    private DatabaseReference mDatabase;
//
//    private EditText message;
//    private Button send;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("News");
//
//        message = (EditText)container.findViewById(R.id.editMessage);
//        send = (Button)container.findViewById(R.id.buttonSend);
//        send.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        final String data = message.getText().toString();
//                                        final User user = new User("Admin");
//                                    }
//                                });
////                mDatabase.child("News").addListenerForSingleValueEvent(
////                        new ValueEventListener() {
////                            @Override
////                            public void onDataChange(DataSnapshot dataSnapshot) {
////                                // Get user value
////
////                                // Write new post
////                                writeNewPost(user.username, data);
////
////
////                                // Finish this Activity, back to the stream
////                                setEditingEnabled(true);
////                                // [END_EXCLUDE]
////                            }
////
////                            @Override
////                            public void onCancelled(DatabaseError databaseError) {
////                                Log.w(TAG, "getUser:onCancelled", databaseError.toException());
////                                // [START_EXCLUDE]
////                                setEditingEnabled(true);
////                                // [END_EXCLUDE]
////                            }
////
////                        });
////
////            }
////
////            private void setEditingEnabled(boolean enabled) {
////                send.setEnabled(enabled);
////                if (enabled) {
////                    send.setVisibility(View.VISIBLE);
////                } else {
////                    send.setVisibility(View.GONE);
////                }
////            }
////            private void writeNewPost(String username, String body) {
////                // Create new post at /user-posts/$userid/$postid and at
////                // /posts/$postid simultaneously
////                String key = mDatabase.child("News").push().getKey();
////                Post post = new Post(username, body);
////                Map<String, Object> postValues = post.toMap();
////
////                Map<String, Object> childUpdates = new HashMap<>();
////                childUpdates.put("/posts/" + key, postValues);
//////                childUpdates.put("/user-posts" + "/" + key, postValues);
////
////                mDatabase.updateChildren(childUpdates);
////            }
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_news, container, false);
//
//        }
//}

