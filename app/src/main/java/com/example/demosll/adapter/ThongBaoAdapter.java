package com.example.demosll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demosll.R;
import com.example.demosll.model.ListItem;
import com.example.demosll.model.ThongBao;

import java.util.List;

public class ThongBaoAdapter extends BaseAdapter {
    Context context;
    List<ThongBao> thongBaos;

    public ThongBaoAdapter(Context context, List<ThongBao> thongBaos) {
        this.context = context;
        this.thongBaos = thongBaos;
    }

    @Override
    public int getCount() {
        return thongBaos.size();
    }

    @Override
    public Object getItem(int position) {
        return thongBaos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thong_bao, parent, false);
        }

        TextView txtTitle = convertView.findViewById(R.id.textTieuDe);

        ThongBao thongBao = thongBaos.get(position);
        txtTitle.setText(thongBao.TieuDe);

        return convertView;
    }
}
