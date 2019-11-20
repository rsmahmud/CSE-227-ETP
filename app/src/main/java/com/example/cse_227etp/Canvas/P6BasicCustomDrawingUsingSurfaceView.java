package com.example.cse_227etp.Canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class P6BasicCustomDrawingUsingSurfaceView extends SurfaceView {
    SurfaceHolder sh;
    Context ct;
    Canvas c;
    public P6BasicCustomDrawingUsingSurfaceView(Context context) {
        super(context);
        ct= context;

        sh = getHolder();
        sh.addCallback(new SurfaceHolder.Callback() {
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                c = sh.lockCanvas();
                onDraw(c);

                sh.unlockCanvasAndPost(c);

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });


    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setTextSize(100f);
        canvas.drawText("Hello",100,100,p);
        p.setColor(Color.YELLOW);

        canvas.drawCircle(50,100,50,p);
        p.setColor(Color.CYAN);


        Rect f1=new Rect(180,180,100,100);
        canvas.drawRect(f1,p);

    }
}
