package com.example.wolfware.agroapp;

import java.io.File;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnTouchListener;
import android.widget.Toast;


public class MainActivity extends Activity {
    //Necesitamos un Boton y un imageView
    private Button bt_hacerfoto;
    private ImageView img;
    private ImageView imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Relacionamos con el XML
        img=(ImageView)this.findViewById(R.id.imageView1);
       // imgv = (ImageView)this.findViewById(R.id.imageView1);
        bt_hacerfoto = (Button) this.findViewById(R.id.button1);

       /* imgv.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Bitmap bmpResult = ((BitmapDrawable)img.getDrawable()).getBitmap();
                try{
                    // Obtener las coordenadas sobre el imageView
                    int x = (int)event.getX();
                    int y = (int) event.getY();

                    //Limitar x, y dentro del bitmap
                    if(x < 0){
                        x = 0;
                    }else if(x > bmpResult.getWidth()-1){
                        x = bmpResult.getWidth()-1;
                    }
                    if(y < 0){
                        y = 0;
                    }else if(y > bmpResult.getHeight()-1){
                        y = bmpResult.getHeight()-1;
                    }
                    // Obtener el codigo decimal del pixel
                    int pixel = bmpResult.getPixel(x,y);
                    int rval = Color.red(pixel);
                    int gval = Color.green(pixel);
                    int bval = Color.blue(pixel);
                    int iColor = Color.argb(255, rval, gval, bval);


                   //txtRes.setBackgroundColor(iColor);

                    // Calculo el CMYK Esto es de YAPA
                    double rr = rval/ 255.0000;
                    double gg = gval / 255.0000;
                    double bb = bval / 255.0000;
                    double black = 1.0000 - Math.max(rr,Math.max(gg,bb));
                    double  cyan = (1-rr-black) / (1-black);
                    double magenta = (1-gg-black) / (1-black);
                    double yellow = (1-bb-black) / (1-black);


                    // Muestro el resultado en HEXA
                    System.out.println(String.format("RGB; #%06X   C:%.4f M:%.4f Y:%.4f K:%.4f", (0xFFFFFF & iColor),cyan,magenta,yellow,black));
                }catch(Exception e){
                    System.out.println("Error en pixel" + e.toString());
                }
                return true;
            }
        });*/


        //Añadimos el Listener Boton
        bt_hacerfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent para llamar a la Camara
                Intent cameraIntent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                //Creamos una carpeta en la memoria del terminal
                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "Agroapp");
                imagesFolder.mkdirs();
                //añadimos el nombre de la imagen
                File image = new File(imagesFolder, "foto.jpg");
                Uri uriSavedImage = Uri.fromFile(image);
                //Le decimos al Intent que queremos grabar la imagen
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                //Lanzamos la aplicacion de la camara con retorno (forResult)
                startActivityForResult(cameraIntent, 1);
            }});
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Comprovamos que la foto se a realizado
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Creamos un bitmap con la imagen recientemente
            //almacenada en la memoria

            Bitmap bMap = BitmapFactory.decodeFile(
                    Environment.getExternalStorageDirectory()+
                            "/AgroApp/"+"foto.jpg");

            //Añadimos el bitmap al imageView para
            //mostrarlo por pantalla
            Toast.makeText(MainActivity.this,"se guardo correctamente",Toast.LENGTH_SHORT).show();
            img.setImageBitmap(bMap);
        }
    }
}