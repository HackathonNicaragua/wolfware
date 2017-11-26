package com.example.wolfware.agroapp;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    ImageView imagen;
    TextView texto;
    Bitmap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto= (TextView) findViewById(R.id.textView);
        imagen= (ImageView) findViewById(R.id.imageView);

        imagen.setDrawingCacheEnabled(true);
        imagen.buildDrawingCache();

        imagen.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE)
                    map=imagen.getDrawingCache();
                    int pixel=map.getPixel((int) event.getX(), (int) event.getY());
                    int r= Color.red(pixel);
                    int g=Color.green(pixel);
                    int b=Color.blue(pixel);

                    texto.setBackgroundColor(Color.rgb(r,g,b));
                    texto.setText("R: "+r+"\n"+"G: "+g+"\n"+"B: "+b);
                return false;
            }
        });


    }


}