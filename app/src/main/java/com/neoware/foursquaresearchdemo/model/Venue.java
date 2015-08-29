package com.neoware.foursquaresearchdemo.model;

public class Venue {

    private final String mVenueName;

    public Venue(String venueName) {
        this.mVenueName = venueName;
    }

    public String getName() {
        return mVenueName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venue that = (Venue) o;

        return !(mVenueName != null ? !mVenueName.equals(that.mVenueName) : that.mVenueName != null);

    }

    @Override
    public int hashCode() {
        return mVenueName != null ? mVenueName.hashCode() : 0;
    }
}
