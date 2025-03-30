package com.example.demosll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demosll.model.ListItem;
import com.example.demosll.R;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    Context context;
    List<ListItem> items;

    public StudentAdapter(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hocsinh_1, parent, false);
        }

        ImageView imgIcon = convertView.findViewById(R.id.imgIcon);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);

        ListItem item = items.get(position);
        imgIcon.setImageResource(item.getImage());
        txtTitle.setText(item.getTitle());

        return convertView;
    }
}
