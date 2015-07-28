package com.samuel.destructo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class SetUpMessageActivity extends ActionBarActivity {

    public static final String TAG = SetUpMessageActivity.class.getSimpleName();

    private Handler handler = new Handler();

    private EditText userMessage;
    private Button sendButton;
    private ListView listChat;
    private String strUserId;
    private ArrayList<Message> mMessages;
    private MessageListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_message);

        Intent intent = getIntent();
        strUserId = ParseUser.getCurrentUser().getObjectId();

        if(intent.hasExtra("sendTo")){
            Log.e(TAG, "Success");
            createMessage();
        }

        //handler.postDelayed(runnable, 100);
    }

    private Runnable runnable = new Runnable(){
        @Override
        public void run(){
            refreshMessages();
            handler.postDelayed(this, 100);
        }
    };

    public void refreshMessages(){
        receiveMessage();
    }

    private void createMessage(){
        userMessage = (EditText) findViewById(R.id.etMessage);
        sendButton = (Button)findViewById(R.id.btSend);
        listChat = (ListView) findViewById(R.id.lvChat);

        mMessages = new ArrayList<Message>();
        mAdapter = new MessageListAdapter(SetUpMessageActivity.this, strUserId, mMessages);
        listChat.setAdapter(mAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = userMessage.getText().toString();
                //ParseObject message = new ParseObject("Message");
                Message outMessage = new Message();
                outMessage.put("userId", strUserId);
                outMessage.put("body", data);
                outMessage.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(SetUpMessageActivity.this, "Message successfully created",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //Error
                            Toast.makeText(SetUpMessageActivity.this, "Error creating message",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                userMessage.setText("");
            }
        });
    }

    private void receiveMessage(){
        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);

        query.setLimit(10);
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<Message>() {
            @Override
            public void done(List<Message> messageList, ParseException e) {
                if(e == null){
                    mMessages.clear();
                    mMessages.addAll(messageList);
                    mAdapter.notifyDataSetChanged();
                    listChat.invalidate();
                }
                else{
                    Log.i("message", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_up_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
