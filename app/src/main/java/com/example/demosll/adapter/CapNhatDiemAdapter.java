package com.example.demosll.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demosll.model.DiemSo;
import com.example.demosll.R;
import com.example.demosll.ui.giaovien.CapNhatDiemGiaoVien;

import java.util.ArrayList;
import java.util.List;

public class CapNhatDiemAdapter extends RecyclerView.Adapter<CapNhatDiemAdapter.DiemViewHolder> {
    private ArrayList<DiemSo> diemSoList;
    private DiemViewHolder viewHolder;
    private final Context context;
    private boolean isChanged = false;
    private boolean isUpdated = true;

    public CapNhatDiemAdapter(Context context, ArrayList<DiemSo> diemSoList) {
        this.context = context;
        this.diemSoList = diemSoList;
    }

    @NonNull
    @Override
    public DiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diem_so_gv, parent, false);
        return new DiemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiemViewHolder holder, int position) {
        DiemSo diemSo = diemSoList.get(position);

        // Không cho phép nhập vào tên cột
        if(diemSo.getMonHoc().equals("Môn học")){
            holder.tvGK1.setEnabled(false);
            holder.tvCK1.setEnabled(false);
            holder.tvGK2.setEnabled(false);
            holder.tvCK2.setEnabled(false);

            holder.tvGK1.setTextColor(Color.BLACK);
            holder.tvCK1.setTextColor(Color.BLACK);
            holder.tvGK2.setTextColor(Color.BLACK);
            holder.tvCK2.setTextColor(Color.BLACK);
        }

        holder.tvMonHoc.setText(diemSo.getMonHoc());
        holder.tvGK1.setText(diemSo.getDiemGK1());
        holder.tvCK1.setText(diemSo.getDiemCK1());
        holder.tvGK2.setText(diemSo.getDiemGK2());
        holder.tvCK2.setText(diemSo.getDiemCK2());
        holder.tvTBMon.setText(diemSo.getDiemTB());

        holder.tvGK1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    isChanged = true;
                    float diemCapNhat = Float.parseFloat(s.toString());
                    diemSo.setDiemGK1(Float.toString(diemCapNhat));

                    if(!diemSo.getDiemGK1().isEmpty() && !diemSo.getDiemCK1().isEmpty() &&
                            !diemSo.getDiemGK2().isEmpty() && !diemSo.getDiemCK2().isEmpty()){
                        diemSo.setDiemTB(String.format("%.2f",
                                (Float.parseFloat(diemSo.getDiemGK1()) + Float.parseFloat(diemSo.getDiemCK1()) +
                                        Float.parseFloat(diemSo.getDiemGK2()) + Float.parseFloat(diemSo.getDiemCK2())) / 4));
                    }
                }catch (NumberFormatException e){
                    isChanged = false;
                    isUpdated = false;
                    Toast.makeText(context, "Bạn đang để trống điểm số", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.tvCK1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    isChanged = true;
                    float diemCapNhat = Float.parseFloat(s.toString());
                    diemSo.setDiemCK1(Float.toString(diemCapNhat));

                    if(!diemSo.getDiemGK1().isEmpty() && !diemSo.getDiemCK1().isEmpty() &&
                            !diemSo.getDiemGK2().isEmpty() && !diemSo.getDiemCK2().isEmpty()){
                        diemSo.setDiemTB(String.format("%.2f",
                                (Float.parseFloat(diemSo.getDiemGK1()) + Float.parseFloat(diemSo.getDiemCK1()) +
                                        Float.parseFloat(diemSo.getDiemGK2()) + Float.parseFloat(diemSo.getDiemCK2())) / 4));
                    }
                }catch (NumberFormatException e){
                    isChanged = false;
                    isUpdated = false;
                    Toast.makeText(context, "Bạn đang để trống điểm số", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.tvGK2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    isChanged = true;
                    float diemCapNhat = Float.parseFloat(s.toString());
                    diemSo.setDiemGK2(Float.toString(diemCapNhat));

                    if(!diemSo.getDiemGK1().isEmpty() && !diemSo.getDiemCK1().isEmpty() &&
                            !diemSo.getDiemGK2().isEmpty() && !diemSo.getDiemCK2().isEmpty()){
                        diemSo.setDiemTB(String.format("%.2f",
                                (Float.parseFloat(diemSo.getDiemGK1()) + Float.parseFloat(diemSo.getDiemCK1()) +
                                        Float.parseFloat(diemSo.getDiemGK2()) + Float.parseFloat(diemSo.getDiemCK2())) / 4));
                    }
                }catch (NumberFormatException e){
                    isChanged = false;
                    isUpdated = false;
                    Toast.makeText(context, "Bạn đang để trống điểm số", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.tvCK2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    isChanged = true;
                    float diemCapNhat = Float.parseFloat(s.toString());
                    diemSo.setDiemCK2(Float.toString(diemCapNhat));

                    if(!diemSo.getDiemGK1().isEmpty() && !diemSo.getDiemCK1().isEmpty() &&
                            !diemSo.getDiemGK2().isEmpty() && !diemSo.getDiemCK2().isEmpty()){
                        diemSo.setDiemTB(String.format("%.2f",
                                (Float.parseFloat(diemSo.getDiemGK1()) + Float.parseFloat(diemSo.getDiemCK1()) +
                                        Float.parseFloat(diemSo.getDiemGK2()) + Float.parseFloat(diemSo.getDiemCK2())) / 4));
                    }
                }catch (NumberFormatException e){
                    isChanged = false;
                    isUpdated = false;
                    Toast.makeText(context, "Bạn đang để trống điểm số", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return diemSoList.size();
    }

    public ArrayList<DiemSo> getList(){
        return diemSoList;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    static class DiemViewHolder extends RecyclerView.ViewHolder {
        TextView tvMonHoc, tvGK1, tvCK1, tvGK2, tvCK2, tvTBMon;

        public DiemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonHoc = itemView.findViewById(R.id.tvMonHoc_gv);
            tvGK1 = itemView.findViewById(R.id.tvGK1_gv);
            tvCK1 = itemView.findViewById(R.id.tvCK1_gv);
            tvGK2 = itemView.findViewById(R.id.tvGK2_gv);
            tvCK2 = itemView.findViewById(R.id.tvCK2_gv);
            tvTBMon = itemView.findViewById(R.id.tvTBMon_gv);
        }
    }

}