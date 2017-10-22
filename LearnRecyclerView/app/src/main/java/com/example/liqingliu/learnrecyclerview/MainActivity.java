/**
 * 第一个例子
 */
package com.example.liqingliu.learnrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        rv = new RecyclerView(this);
        setContentView(rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //使用Adapter填充界面
        rv.setAdapter(new RecyclerView.Adapter() {
            class ViewHolder extends RecyclerView.ViewHolder{
                private TextView tv;

                public ViewHolder(TextView itemView) {
                    super(itemView);
                    tv = itemView;
                }

                public TextView getTv() {
                    return tv;
                }
            }

            private String[] data =new String[] {"A","B","C"};

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // 创建ViewHolder
                return new ViewHolder(new TextView(parent.getContext()));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((ViewHolder)holder).getTv().setText(data[position]);
            }

            @Override
            public int getItemCount() {
                return data.length;
            }
        });
    }
}
