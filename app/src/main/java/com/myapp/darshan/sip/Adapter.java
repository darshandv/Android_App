package com.myapp.darshan.sip;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;
import com.myapp.darshan.sip.ChatMessage;

import java.util.ArrayList;

/**
 * Created by darshan on 4/4/18.
 */

public class Adapter extends ArrayAdapter {
    public Adapter(@NonNull Context context, @NonNull ArrayList<ChatMessage> objects) {
        super( context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

       View listItemView=convertView;
       if (listItemView==null){
           listItemView=LayoutInflater.from(getContext()).inflate(R.layout.message,parent,false);
       }

        ChatMessage message= (ChatMessage) getItem(position);

        TextView user=(TextView)listItemView.findViewById(R.id.message_user);
        TextView time= (TextView)listItemView.findViewById(R.id.message_time);
        TextView text=(TextView)listItemView.findViewById(R.id.message_text);

        user.setText(message.getMessageUser());
        text.setText( message.getMessageText());
        time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                message.getMessageTime()));

        return listItemView;
    }
}
