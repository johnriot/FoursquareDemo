package com.neoware.foursquaresearchdemo.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Venues implements List<Venue> {

    private final List<Venue> mVenues;

    public Venues(List<Venue> venues) {
        mVenues = venues;
    }

    @Override
    public int size() {
        return mVenues.size();
    }

    @Override
    public boolean isEmpty() {
        return mVenues.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return mVenues.contains(o);
    }

    @Override
    public Iterator<Venue> iterator() {
        return mVenues.iterator();
    }

    @Override
    public Object[] toArray() {
        return mVenues.toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public <T> T[] toArray(T[] ts) {
        return mVenues.toArray(ts);
    }

    @Override
    public boolean add(Venue Venue) {
        return mVenues.add(Venue);
    }

    @Override
    public boolean remove(Object o) {
        return mVenues.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return mVenues.containsAll(objects);
    }

    @Override
    public boolean addAll(Collection<? extends Venue> repositories) {
        return this.mVenues.addAll(repositories);
    }

    @Override
    public boolean addAll(int i, Collection<? extends Venue> repositories) {
        return this.mVenues.addAll(i, repositories);
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        return mVenues.removeAll(objects);
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        return mVenues.retainAll(objects);
    }

    @Override
    public void clear() {
        mVenues.clear();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        return mVenues.equals(o);
    }

    @Override
    public int hashCode() {
        return mVenues.hashCode();
    }

    @Override
    public Venue get(int i) {
        return mVenues.get(i);
    }

    @Override
    public Venue set(int i, Venue Venue) {
        return mVenues.set(i, Venue);
    }

    @Override
    public void add(int i, Venue Venue) {
        mVenues.add(i, Venue);
    }

    @Override
    public Venue remove(int i) {
        return mVenues.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return mVenues.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return mVenues.lastIndexOf(o);
    }

    @Override
    public ListIterator<Venue> listIterator() {
        return mVenues.listIterator();
    }

    @Override
    public ListIterator<Venue> listIterator(int i) {
        return mVenues.listIterator(i);
    }

    @Override
    public List<Venue> subList(int i, int i2) {
        return mVenues.subList(i, i2);
    }
}
