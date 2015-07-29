package com.samuel.destructo;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Samuel on 7/28/2015.
 */
public class MessageListAdapter extends ArrayAdapter<Message> {
    private String mUser;

    public MessageListAdapter(Context context, String user, List<Message> messages){
        super(context, 0, messages);
        this.mUser = user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parentView){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.chat_message, parentView, false);
            final ViewHolder holder = new ViewHolder();
            holder.imageLeft = (ImageView)convertView.findViewById(R.id.ivProfileLeft);
            holder.imageRight = (ImageView)convertView.findViewById(R.id.ivProfileRight);
            holder.body = (TextView)convertView.findViewById(R.id.tvBody);
            convertView.setTag(holder);

        }
        final Message message = (Message)getItem(position);
        final ViewHolder holder = (ViewHolder)convertView.getTag();
        final boolean verifyUser = message.getUser().equals(mUser);
        //final ImageView profileView;
        if(verifyUser){
            holder.imageRight.setVisibility(View.VISIBLE);
            holder.imageLeft.setVisibility(View.GONE);
            holder.body.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            //profileView = holder.imageRight;
        }
        else{
            holder.imageLeft.setVisibility(View.VISIBLE);
            holder.imageRight.setVisibility(View.GONE);
            holder.body.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            //profileView = holder.imageLeft;
        }
        //Picasso.with(getContext()).load(getProfileUrl(message.getUserId())).into(profileView);
        holder.body.setText(message.getBody());
        return convertView;
    }

    //Avatar to be displayed along message
    /*private static String getProfileUrl(final String user){
        String hex = "";

    }*/
    final class ViewHolder{
        public ImageView imageLeft;
        public ImageView imageRight;
        public TextView body;
    }

}
