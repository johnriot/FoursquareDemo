package com.neoware.foursquaresearchdemo.controller;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.neoware.foursquaresearchdemo.R;

public class LayoutController {
    private FloatingActionButton mMiddleFab;
    private FloatingActionButton mBottomFab;
    private TextInputLayout mTextInputLayout;
    private Context mContext;
    private boolean mLoadStarted;

    public LayoutController(Context context, FloatingActionButton middleFab, FloatingActionButton bottomFab, TextInputLayout textInputLayout) {
        mContext = context.getApplicationContext();
        mMiddleFab = middleFab;
        mBottomFab = bottomFab;
        mTextInputLayout = textInputLayout;
    }

    public void onMiddleFabClick() {
        if(mLoadStarted) {
            mMiddleFab.setImageResource(R.drawable.ic_search);
            moveMiddleFabDown();
            mLoadStarted = false;
        }
        else {
            // Pressing the Middle FAB for the second time simulates what will happen in the future when
            // results are loaded. This behaviour will soon change.
            mMiddleFab.setImageResource(R.drawable.ic_loading);
            Animation rotate = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
            mMiddleFab.startAnimation(rotate);
            mLoadStarted = true;
        }
    }

    public void onBottomFabClick() {
        Animation moveDown = AnimationUtils.loadAnimation(mContext, R.anim.move_up);
        moveDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTextInputLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBottomFab.setVisibility(View.INVISIBLE);
                mMiddleFab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mBottomFab.startAnimation(moveDown);
    }

    private void moveMiddleFabDown() {
        Animation moveDown = AnimationUtils.loadAnimation(mContext, R.anim.move_down);
        moveDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTextInputLayout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mMiddleFab.setVisibility(View.INVISIBLE);
                mBottomFab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        mMiddleFab.startAnimation(moveDown);
    }
}
