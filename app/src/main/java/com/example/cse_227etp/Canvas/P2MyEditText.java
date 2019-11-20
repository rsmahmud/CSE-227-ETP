package com.example.cse_227etp.Canvas;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

import com.example.cse_227etp.R;

public class P2MyEditText extends AppCompatEditText {
    Drawable darkButton, lightButton;   //2 vector images
    /*from Xml AttributeSet is done which internally calling
     2 parameterised constructor */
    public P2MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();  //it is further calling the init() of this class
    }

    void init()
    {
        //to fetch two images from the drawable i.e. dark and light button
        lightButton = ResourcesCompat.getDrawable(getResources(), R.drawable.p2light_black,null);
        darkButton = ResourcesCompat.getDrawable(getResources(), R.drawable.p2dark_black,null);
        addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {  //start count after
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {  //start before count
                showButton();
            }
            @Override
            public void afterTextChanged(Editable editable)
            {//Do something if change is done
            }
        });

        setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                boolean isClicked =false;
                float clearButtonStart = getWidth()-getPaddingEnd()-lightButton.getIntrinsicWidth();
                if(motionEvent.getX() > clearButtonStart)
                {
                    isClicked = true;
                }
                if(isClicked) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            getText().clear();
                            break;
                        case MotionEvent.ACTION_UP:
                            showDarkButton();
                            break;
                    }
                }
                return true;
            }
        });
    }


    public void showButton()
    {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,lightButton,null);
    }

    public void showDarkButton()
    {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,darkButton,null);
    }
}
