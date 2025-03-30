package com.example.demosll.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demosll.model.DiemSo;
import com.example.demosll.R;

import java.util.List;

public class DiemAdapter extends RecyclerView.Adapter<DiemAdapter.DiemViewHolder> {
    private List<DiemSo> diemSoList;

    public DiemAdapter(List<DiemSo> diemSoList) {
        this.diemSoList = diemSoList;
    }

    @NonNull
    @Override
    public DiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diem_so, parent, false);
        return new DiemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiemViewHolder holder, int position) {
        DiemSo diemSo = diemSoList.get(position);
        holder.tvMonHoc.setText(diemSo.getMonHoc());
        holder.tvGK1.setText(diemSo.getDiemGK1());
        holder.tvCK1.setText(diemSo.getDiemCK1());
        holder.tvGK2.setText(diemSo.getDiemGK2());
        holder.tvCK2.setText(diemSo.getDiemCK2());
        holder.tvTBMon.setText(diemSo.getDiemTB());
    }

    @Override
    public int getItemCount() {
        return diemSoList.size();
    }

    static class DiemViewHolder extends RecyclerView.ViewHolder {
        TextView tvMonHoc, tvGK1, tvCK1, tvGK2, tvCK2, tvTBMon;

        public DiemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonHoc = itemView.findViewById(R.id.tvMonHoc);
            tvGK1 = itemView.findViewById(R.id.tvGK1);
            tvCK1 = itemView.findViewById(R.id.tvCK1);
            tvGK2 = itemView.findViewById(R.id.tvGK2);
            tvCK2 = itemView.findViewById(R.id.tvCK2);
            tvTBMon = itemView.findViewById(R.id.tvTBMon);
        }
    }
}