package com.samuel.destructo;

import android.os.Bundle;
import android.support.v4.app.ListFragment; //import this version b/c of ViewPager usage
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Samuel on 7/2/2015.
 */
public class InboxFragment extends ListFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);

        return rootView;
    }
}
