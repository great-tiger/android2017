package com.example.liqingliu.learncomponents;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SingleChoose extends Activity {

    private RadioGroup group;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_choose);
        submit = findViewById(R.id.submit);
        group = findViewById(R.id.group);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected="";
                switch (group.getCheckedRadioButtonId()){
                    case R.id.A:
                        selected = "A";
                        break;
                    case R.id.B:
                        selected = "B";
                        break;
                    case R.id.C:
                        selected ="C";
                        break;
                    case R.id.D:
                        selected = "D";
                        break;
                    case R.id.E:
                        selected = "E";
                        break;

                }
                Toast.makeText(SingleChoose.this,selected,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
