package com.myapp.darshan.sip;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by darshan on 15/3/18.
 */

public class MessageData {
    public String body;

    public MessageData(){

    }

    public MessageData(String body){
        this.body=body;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("body", body);

        return result;
    }
}
