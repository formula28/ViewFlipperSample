package com.formula.sample.viewflippersample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

/**
 * Created by @formula on 2016/04/17.
 */
public class MainActivity extends Activity {
    private ViewFlipper mViewFlipper = null;
    private GestureDetector mGestureDetector = null;
    private static final float FLICK_DETECT_MIN_DISTANCE = 200.0f;
    private static final float FLICK_DETECT_MIN_VELOCITY = 700.0f;
//    private Animation mSlideInFromLeft = null;
//    private Animation mSlideInFromRight = null;
//    private Animation mSlideOutToLeft = null;
//    private Animation mSlideOutToRight = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Viewの準備.
        findView();
//        loadAnim();
        prepareViewListener();
    }

    private void findView() {
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
    }
//    private void loadAnim() {
//        mSlideInFromLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_left);
//        mSlideInFromRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_right);
//        mSlideOutToLeft = AnimationUtils.loadAnimation(this, R.anim.slide_out_to_left);
//        mSlideOutToRight = AnimationUtils.loadAnimation(this, R.anim.slide_out_to_right);
//    }
    private void prepareViewListener() {
        // フリック検出.
        mGestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener(){
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                Log.d(App.LOGTAG, String.format("motionEvent = %s", motionEvent));
                return motionEvent != null;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                Log.d(App.LOGTAG, String.format("motionEvent = %s", motionEvent));
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Log.d(App.LOGTAG, String.format("motionEvent = %s", motionEvent));
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                Log.d(App.LOGTAG, String.format("motionEvent = %s", motionEvent));
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                Log.d(App.LOGTAG, String.format("motionEvent = %s", motionEvent));
            }

            // フリックイベント
            @Override
            public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
                Log.d(App.LOGTAG, String.format("X1 = %f, X2 = %f, Y1 = %f, Y2 = %f, vX = %f, vY = %f",
                        event1.getX(), event2.getX(), event1.getY(), event2.getY(), velocityX, velocityY));
                if  (event1.getX() - event2.getX() > FLICK_DETECT_MIN_DISTANCE && Math.abs(velocityX) > FLICK_DETECT_MIN_VELOCITY) {
                    //right to left.
                    mViewFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_from_right);
                    mViewFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_to_left);
                    mViewFlipper.showNext();
                }
                else if (event2.getX() - event1.getX() > FLICK_DETECT_MIN_DISTANCE && Math.abs(velocityX) > FLICK_DETECT_MIN_VELOCITY) {
                    //left to right.
                    mViewFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_from_left);
                    mViewFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_to_right);
                    mViewFlipper.showPrevious();
                }
                return false;
            }
        });
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(App.LOGTAG, "onTouch ViewFlipper");
                return mGestureDetector.onTouchEvent(motionEvent);
            }
        });
    }
}
