package com.neoware.foursquaresearchdemo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neoware.foursquaresearchdemo.model.Venue;

public class VenuesAdapter extends ArrayListAdapter<Venue> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        ((TextView) convertView).setText(getItem(position).getName());
        return convertView;
    }
}