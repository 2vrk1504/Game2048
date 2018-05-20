package com.irodos.game2048;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Game g;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        g = new Game(this);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        g.generate_rand_box(false);
    }
    @Override
    protected  void onResume(){
        super.onResume();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                Log.d("PROGRAMMER", "Entered try");
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            Log.d("PROGRAMMER", "Swipe Right");
                            onSwipeRight();
                        } else {
                            Log.d("PROGRAMMER", "Swipe Left");
                            onSwipeLeft();
                        }
                        result = true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        Log.d("PROGRAMMER", "Swipe down");
                        onSwipeBottom();
                    } else {
                        Log.d("PROGRAMMER", "Swipe up");
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                Log.d("PROGRAMMER", "Swipe fail");
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight() {
        g.right();
    }

    public void onSwipeLeft() {
        g.left();
    }

    public void onSwipeTop() {
        g.up();
    }

    public void onSwipeBottom() {
        g.down();
    }
}

