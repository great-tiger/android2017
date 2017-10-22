/**
 * 演示使用布局文件定义列表相
 *
 */
package com.example.liqingliu.learnrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Main2Activity extends Activity {

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
                private TextView title,content;

                public ViewHolder(View root) {
                    super(root);
                    this.title = root.findViewById(R.id.title);
                    this.content = root.findViewById(R.id.content);
                }


                public TextView getTitle() {
                    return title;
                }

                public TextView getContent() {
                    return content;
                }
            }

            private CellData[] data =new CellData[] {
                    new CellData("ATitle","AConent"),
                    new CellData("BTitle","BContent")
            };

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // 创建ViewHolder
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell,null));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((ViewHolder)holder).getTitle().setText(data[position].title);
                ((ViewHolder)holder).getContent().setText(data[position].content);
            }

            @Override
            public int getItemCount() {
                return data.length;
            }
        });
    }
}
