package com.example.cse_227etp.Canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.cse_227etp.R;

public class P4CustomView extends View {
    //first topic: create Rectangle and fan on canvas
    Paint p;
    int x=0;  //start angle of arc initially provided 0 for first arc

    //second topic: show an image on the canvas
    Bitmap bp;
    public P4CustomView(Context context) //from main activity control comes to this constructor
    {
        super(context);
        init();   //calling another user defined method init()
    }
    public void init()
    {
        //to get paint brush
        p = new Paint();

        //for image show
        bp = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
    }
    @Override
    protected void onDraw( Canvas canvas)
    {
        canvas.drawColor(Color.RED);  //canvas color
        p.setColor(Color.YELLOW);  // paint brush color

        //drawRect(-----) to draw rectagle with paint brush p
        canvas.drawRect(100,100,500,500,p);

        p.setColor(Color.GREEN);  //change the color of paint brush for arc fan like structure

        //drawArc(-----) to draw Arc with paint brush p
        canvas.drawArc(500,500,800,800,x,30,true,p);
        canvas.drawArc(500,500,800,800,x+120,30,true,p);
        canvas.drawArc(500,500,800,800, x+240,30,true,p);

        //to add image in the canvas drawBitmap(----)
        canvas.drawBitmap(bp,x,500,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            /*whenever the screen is touched startFan
            method will be called */
            case MotionEvent.ACTION_DOWN:
                startFan();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    void startFan()
    {        //move the start angle to 5 point ahead
        x = x + 5;
        invalidate(); // then again call onDraw() explicitly
    }
}
