package com.example.cse_227etp.Canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class P3FingerGesture extends View {
    Paint p;
    Path mpath;  //reference to create path as finger is moving
    public P3FingerGesture(Context context){
        super(context);
        p = new Paint();
        mpath = new Path();
        p.setColor(Color.BLACK);  //color of painting
        p.setStyle(Paint.Style.STROKE);  //style of painting
        p.setStrokeWidth(10f);    //width of paintybrush
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.YELLOW);  //color of canvas
        canvas.drawPath(mpath,p);    //static method of canvas to create the path with paint brush p
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction())
        {
            //when finger clicks on the screen, move the cursor to that point of touch
            case MotionEvent.ACTION_DOWN:
                mpath.moveTo(event.getX(), event.getY());
                break;
            ////when finger moves on the screen, draw a line to that x-y coordinate movement
            case MotionEvent.ACTION_MOVE:
                mpath.lineTo(event.getX(), event.getY());
                break;
        }
        invalidate();  //this predefined method is used to call onDraw() again and again explicitly
        return true;
    }
}
