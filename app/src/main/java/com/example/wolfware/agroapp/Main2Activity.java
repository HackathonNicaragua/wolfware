package com.example.wolfware.agroapp;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    ImageView imgimagen;
    Button btnfoto;
    Bitmap map;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_principal);
        //
        btnfoto = (Button) findViewById(R.id.btnfoto);
        imgimagen = (ImageView) findViewById(R.id.imgfoto);
        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                llamarIntent();
            }
        });

        imgimagen.setDrawingCacheEnabled(true);
        imgimagen.buildDrawingCache();
        imgimagen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE)
                    map=imgimagen.getDrawingCache();
                int pixel=map.getPixel((int) event.getX(), (int) event.getY());
                int r= Color.red(pixel);
                int g=Color.green(pixel);
                int b=Color.blue(pixel);

                return false;
            }
        });


    }


    private void llamarIntent () {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgimagen.setImageBitmap(imageBitmap);
        }
    }
}