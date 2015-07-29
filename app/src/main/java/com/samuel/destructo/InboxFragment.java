package com.samuel.destructo;

import android.os.Bundle;
import android.support.v4.app.ListFragment; //import this version b/c of ViewPager usage
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Samuel on 7/2/2015.
 */
public class InboxFragment extends ListFragment{


    protected List<Message> mMessages;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        //Should be using key id and not actual names

        ParseQuery<Message> query = new ParseQuery<Message>(Message.class);
        //ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
        //query.whereEqualTo(ParseConstants.KEY_RECIPIENT_IDS, ParseUser.getCurrentUser().getObjectId());
        query.whereEqualTo("recipientId", ParseUser.getCurrentUser().getObjectId());
        query.addDescendingOrder(ParseConstants.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Message>() {
            @Override
            public void done(List<Message> messagesList, ParseException e) {
                if (e == null) {
                    //Found messages
                    mMessages = messagesList;

                    String[] usernames = new String[mMessages.size()];
                    int i = 0;
                    for (Message singleMessage : mMessages) {
                        //usernames[i] = singleMessage.getString(ParseConstants.KEY_SENDER_NAME);
                        usernames[i] = singleMessage.getString("userId");
                        i++;
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getListView().getContext(),
                            android.R.layout.simple_list_item_1,
                            usernames);
                    setListAdapter(adapter);
                }
                else {

                }
            }
        });

    }

}
