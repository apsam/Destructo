package com.samuel.destructo;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Samuel on 7/28/2015.
 */
@ParseClassName("Message")
public class Message extends ParseObject {

    public Message(){
        super();
    }

    public String getUser(){
        return getString("user");
    }

    public String getBody(){
        return getString("body");
    }

    public void setUser(String user){
        put("user", user);
    }

    public void setBody(String body){
        put("body", body);
    }
}
