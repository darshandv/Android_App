package com.myapp.darshan.sip;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by darshan on 29/3/18.
 */

public class DCforum extends AppCompatActivity{
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
    }

}
