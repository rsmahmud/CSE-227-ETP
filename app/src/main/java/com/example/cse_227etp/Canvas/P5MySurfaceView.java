package com.example.cse_227etp.Canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class P5MySurfaceView extends SurfaceView implements Runnable {
    SurfaceHolder sh;   //SurfaceHolder is the one to hold multiple canvas and it works on multiple background threads.
    Paint p ;
    int x=0;
    Canvas can;
    boolean isRunning ;  //to check whether the fan is running or not
    Thread t = null;
    public P5MySurfaceView(Context context)  //control will tranfered to this constructor body from the main activity
    {
        super(context);
        init();
    }
    public P5MySurfaceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    void init()
    {
        sh = getHolder();  //To get the Surface Holder interface which has three callback methods to override.
        p = new Paint();
        sh.addCallback(new Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder)
            {
                startThread();
            }
            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2)
            {

            }
            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder)
            {

            }
        });
    }
    public void startThread()    //once the surface is ready to hold the canvas , call one background thread
    {//isRunning = true;
        t = new Thread(this);
        t.start();   //it will call run method
    }
    @Override
    protected void onDraw(Canvas canvas) //to draw fan
    {
        if(sh.getSurface().isValid())
        {
            canvas.drawColor(Color.RED);
            p.setColor(Color.GREEN);
            canvas.drawArc(500, 500, 800, 800, x, 30, true, p);
            canvas.drawArc(500, 500, 800, 800, x + 120, 30, true, p);
            canvas.drawArc(500, 500, 800, 800, x + 240, 30, true, p);
            x = x + 10;
        }
    }
    @SuppressLint("WrongCall")
    @Override
    public void run() {
        do
        {
            try {
                synchronized (this)  //sync the current thread so that it get executed completely
                {
                    Thread.sleep(300);
                    can = sh.lockCanvas();  //lock the current canvas on surfaceholder and then call onDraw() for that locked canvas
                    onDraw(can);  //onDraw() will only work if supressLintis called for "wrong call"
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                //once done the drawing and unlock the canvas
                if(can != null) {
                    sh.unlockCanvasAndPost(can);
                    postInvalidate();  // and then call onDraw() again explicitly . On SurfaceHolder, postInvalidate() is called to call onDraw() explicitly
                }
            }
        }while (isRunning);  //keep rotating while the isRunning is true.
    }
}
