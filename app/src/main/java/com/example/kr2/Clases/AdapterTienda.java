package com.example.kr2.Clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kr2.R;

import java.util.ArrayList;

public class AdapterTienda extends RecyclerView.Adapter<AdapterTienda.ViewHolder> {
    int cantidad;

    public AdapterTienda(int numero)
    {
       cantidad = numero;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtienda,null,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
