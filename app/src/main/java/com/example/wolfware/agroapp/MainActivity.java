package com.example.wolfware.agroapp;

<<<<<<< HEAD
import android.annotation.SuppressLint;
=======
import android.graphics.Color;
>>>>>>> 2730a7d0327f641be197d45a2471e3dc980dbd24
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

<<<<<<< HEAD
        touchedXY = (TextView)findViewById(R.id.xy);
        invertedXY = (TextView)findViewById(R.id.invertedxy);
        imgSize = (TextView)findViewById(R.id.size);
        colorRGB = (TextView)findViewById(R.id.colorrgb);
        imgSource1 = (ImageView)findViewById(R.id.source1);
        imgSource2 = (ImageView)findViewById(R.id.source2);

        imgSource1.setOnTouchListener(imgSourceOnTouchListener);
        imgSource2.setOnTouchListener(imgSourceOnTouchListener);

    }

    OnTouchListener imgSourceOnTouchListener = new OnTouchListener(){

        @SuppressLint("SetTextI18n")
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            float eventX = event.getX();
            float eventY = event.getY();
            float[] eventXY = new float[] {eventX, eventY};

            Matrix invertMatrix = new Matrix();
            ((ImageView)view).getImageMatrix().invert(invertMatrix);

            invertMatrix.mapPoints(eventXY);
            int x = Integer.valueOf((int)eventXY[0]);
            int y = Integer.valueOf((int)eventXY[1]);

            touchedXY.setText("touched position: " + String.valueOf(eventX) + " / " + String.valueOf(eventY));
            invertedXY.setText("touched position: " + String.valueOf(x) + " / " + String.valueOf(y));

            Drawable imgDrawable = ((ImageView)view).getDrawable();
            Bitmap bitmap = ((BitmapDrawable)imgDrawable).getBitmap();

            imgSize.setText("drawable size: " + String.valueOf(bitmap.getWidth()) + " / " + String.valueOf(bitmap.getHeight()));

            //Limit x, y range within bitmap
            if(x < 0){
                x = 0;
            }else if(x > bitmap.getWidth()-1){
                x = bitmap.getWidth()-1;
            }

            if(y < 0){
                y = 0;
            }else if(y > bitmap.getHeight()-1){
                y = bitmap.getHeight()-1;
=======
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
>>>>>>> 2730a7d0327f641be197d45a2471e3dc980dbd24
            }
        });


    }


}