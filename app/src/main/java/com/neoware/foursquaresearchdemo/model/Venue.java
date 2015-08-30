package com.neoware.foursquaresearchdemo.model;

public class Venue {

    private final String mVenueName;
    private final String mPhotoUrl;

    public Venue(String venueName, String photoUrl) {
        mVenueName = venueName;
        mPhotoUrl = photoUrl;
    }

    public String getName() {
        return mVenueName;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venue that = (Venue) o;
        boolean venueNameIsEqual = !(mVenueName != null ? !mVenueName.equals(that.mVenueName) : that.mVenueName != null);
        boolean photoUrlIsEqual = !(mPhotoUrl != null ? !mPhotoUrl.equals(that.mPhotoUrl) : that.mPhotoUrl != null);
        return venueNameIsEqual && photoUrlIsEqual;

    }

    @Override
    public int hashCode() {
        int venueHash = mVenueName != null ? mVenueName.hashCode() : 0;
        int photoUrlHash = mPhotoUrl != null ? mPhotoUrl.hashCode() : 0;
        return venueHash + photoUrlHash;
    }
}
