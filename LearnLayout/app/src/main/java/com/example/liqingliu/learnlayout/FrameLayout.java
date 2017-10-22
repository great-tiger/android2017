package com.example.liqingliu.learnlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FrameLayout extends Activity implements View.OnClickListener {

    private android.widget.FrameLayout root;
    private ImageView img1;
    private ImageView img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        root = findViewById(R.id.container);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);

        root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(img1.getVisibility() != View.VISIBLE) {
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.INVISIBLE);
        }else{
            img2.setVisibility(View.VISIBLE);
            img1.setVisibility(View.INVISIBLE);
        }
    }
}
