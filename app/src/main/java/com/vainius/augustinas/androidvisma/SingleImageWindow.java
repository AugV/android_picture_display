package com.vainius.augustinas.androidvisma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class SingleImageWindow extends AppCompatActivity {
    ImageView myImage;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_image_window);

        ImageLoader imageLoaderSingle = ImageLoader.getInstance();

        //gauna paspausto paveiksliuko URL is ImageAdapter
        url = getIntent().getStringExtra("image_url");
        myImage = findViewById(R.id.myImage);

        //uzloadina gauta url kaip image'a i naujo lango ImageView
        imageLoaderSingle.displayImage(url, myImage);

    }

}
