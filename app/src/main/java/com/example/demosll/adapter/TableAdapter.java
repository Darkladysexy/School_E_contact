package com.example.demosll.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demosll.model.HocPhi;
import com.example.demosll.R;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private List<HocPhi> dataList;

    public TableAdapter(List<HocPhi> dataList){
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HocPhi hp = dataList.get(position);
        holder.tvTenPhi.setText(hp.getTenPhi());
        holder.tvHanDong.setText(hp.getHanDong());
        holder.tvSoTien.setText(hp.getSoTien());
        holder.tvTrangThai.setText(hp.getTrangThai());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hocphi, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenPhi, tvSoTien, tvTrangThai, tvHanDong;

        public ViewHolder(View view){
            super(view);
            tvTenPhi = view.findViewById(R.id.textView_TenPhi);
            tvHanDong = view.findViewById(R.id.textView_HanDong);
            tvSoTien = view.findViewById(R.id.textView_SoTienTong);
            tvTrangThai = view.findViewById(R.id.textView_TrangThai);
        }
    }
}
