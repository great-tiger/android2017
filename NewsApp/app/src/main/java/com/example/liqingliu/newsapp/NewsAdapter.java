package com.example.liqingliu.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liqingliu on 17/11/10.
 */

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<NewsItem> list;

    public NewsAdapter(Context context, List<NewsItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = LayoutInflater.from(context).inflate(R.layout.list_view_cell,null,true);
        ImageView iv = listItem.findViewById(R.id.image);
        TextView title = listItem.findViewById(R.id.title);
        TextView desc = listItem.findViewById(R.id.desc);
        TextView time = listItem.findViewById(R.id.time);
        NewsItem item = list.get(position);
        title.setText(item.getTitle());
        desc.setText(item.getDesc());
        time.setText(item.getTime());
        HttpUtils.setImage(iv,item.getPicUrl());
        return listItem;
    }
}
