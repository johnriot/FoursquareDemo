package com.neoware.foursquaresearchdemo.controller;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import com.neoware.foursquaresearchdemo.R;

public class LayoutController {
    private FloatingActionButton mMiddleFab;
    private FloatingActionButton mBottomFab;
    private TextInputLayout mTextInputLayout;
    private RecyclerView mRecyclerView;
    private Context mContext;

    public LayoutController(Context context, FloatingActionButton middleFab, FloatingActionButton bottomFab,
                            TextInputLayout textInputLayout, RecyclerView recyclerView) {
        mContext = context.getApplicationContext();
        mMiddleFab = middleFab;
        mBottomFab = bottomFab;
        mTextInputLayout = textInputLayout;
        mRecyclerView = recyclerView;
    }

    public void onMiddleFabClick() {
        hideKeyboard();

        mMiddleFab.setImageResource(R.drawable.ic_loading);
        Animation rotate = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
        mMiddleFab.startAnimation(rotate);
    }

    public void onBottomFabClick() {
        Animation moveDown = AnimationUtils.loadAnimation(mContext, R.anim.move_up);
        moveDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mRecyclerView.setAlpha(0.2f);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTextInputLayout.setVisibility(View.VISIBLE);
                mRecyclerView.setFocusable(false);
                ignoreRecyclerViewTouches();
                mTextInputLayout.setFocusable(true);

                showKeyboard();

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
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mMiddleFab.startAnimation(moveDown);
    }

    public void onSearchResponse() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setAlpha(1.0f);
        stopIgnoringRecyclerViewTouches();
        mMiddleFab.setImageResource(R.drawable.ic_search);
        moveMiddleFabDown();
    }

    public void onSearchReset() {
        mMiddleFab.clearAnimation();
        mMiddleFab.setImageResource(R.drawable.ic_search);
        showKeyboard();
        mTextInputLayout.setFocusable(true);
    }

    private void ignoreRecyclerViewTouches() {
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(android.view.View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void stopIgnoringRecyclerViewTouches() {
        mRecyclerView.setOnTouchListener(null);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mMiddleFab.getWindowToken(), 0);
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
