package com.neoware.foursquaresearchdemo.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neoware.foursquaresearchdemo.R;
import com.neoware.foursquaresearchdemo.model.Venue;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VenuesAdapter extends RecyclerView.Adapter<VenueHolder> {
    private final List<Venue> mVenues;
    private final Context mContext;

    public VenuesAdapter(Context context) {
        mVenues = new ArrayList<>();
        mContext = context.getApplicationContext();
    }

    @Override
    public VenueHolder onCreateViewHolder(ViewGroup parent, int pos) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.venue_card, parent, false);
        return new VenueHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(VenueHolder holder, int pos) {
        Venue venue = mVenues.get(pos);
        holder.bindVenue(venue);
    }

    @Override
    public int getItemCount() {
        return mVenues.size();
    }

    public void setItems(Collection<Venue> venues) {
        this.mVenues.clear();
        this.mVenues.addAll(venues);
        notifyDataSetChanged();
    }
}

class VenueHolder extends RecyclerView.ViewHolder {
    private final TextView mPlaceName;
    private final ImageView mImageView;
    private final Picasso mPicasso;
    private final int mTransparentBackground;
    private final int mDefaultBackgroundColor;

    public VenueHolder(View itemView, Context context) {
        super(itemView);
        mPlaceName = (TextView) itemView.findViewById(R.id.venue_name_tv);
        mImageView = (ImageView) itemView.findViewById(R.id.venue_iv);
        mPicasso = Picasso.with(context);
        mDefaultBackgroundColor = context.getResources().getColor(R.color.default_text_background);
        mTransparentBackground = context.getResources().getColor(R.color.transparent_background);
    }

    public void bindVenue(Venue venue) {
        mPlaceName.setBackgroundColor(mDefaultBackgroundColor);
        mPlaceName.setText(venue.getName());
        mPicasso.load(venue.getPhotoUrl())
                .placeholder(R.drawable.location_placeholder_image)
                .into(mImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        mPlaceName.setBackgroundColor(mTransparentBackground);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}



