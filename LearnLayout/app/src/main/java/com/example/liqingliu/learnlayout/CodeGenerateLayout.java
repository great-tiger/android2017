package com.example.liqingliu.learnlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class CodeGenerateLayout extends Activity {
    LinearLayout root;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);

        for(int i = 0; i < 5; i++) {
            button = new Button(this);
            button.setText("Click Me");
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    root.removeView(v);
                }
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            root.addView(button,lp);
            //root.addView(button,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }
}
