package com.example.cse_227etp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cse_227etp.Canvas.P3FingerGesture;
import com.example.cse_227etp.Canvas.P4CustomView;
import com.example.cse_227etp.Canvas.P5MySurfaceView;
import com.example.cse_227etp.Canvas.P6BasicCustomDrawingUsingSurfaceView;

public class CanvasActivity extends AppCompatActivity {
    //P2MyEditText et;
    P3FingerGesture md;
    P4CustomView cv;
    P5MySurfaceView msv;
    P6BasicCustomDrawingUsingSurfaceView cdu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        /*  you need to write one at a time, and comment the other calls*/
        // md is to call constructor of P3MyDrawing
        md=new P3FingerGesture(this);    //call the class from main activity java class
        setContentView(md);     //used to show this canvas drawing on the activity

        // cv is to call constructor of P4CustomView
        cv=new P4CustomView(this);
        setContentView(cv);      //used to show this canvas drawing on the activity

        // msv is to call constructor of P5MySurfaceView
        msv=new P5MySurfaceView(this);
        setContentView(msv);    //used to show this canvas drawing on the activity


        //cdu is to call constructor of P6BasicCustomDrawingUsingSurfaceView
        cdu=new P6BasicCustomDrawingUsingSurfaceView(this);
        setContentView(msv);
    }
}
