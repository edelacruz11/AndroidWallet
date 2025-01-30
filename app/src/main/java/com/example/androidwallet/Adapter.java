package com.example.androidwallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Crypto> data;

    public Adapter(List<Crypto> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crypto_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Crypto crypto = data.get(position);
        holder.cryptoName.setText(crypto.getNombre());
        holder.cryptoAmount.setText(crypto.getCantidad() + " " + crypto.getNombre().substring(0, 3));
        holder.cryptoValue.setText(crypto.getValorEnEuros() + "€");
        holder.cryptoImage.setImageResource(crypto.getImagenResId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cryptoImage;
        TextView cryptoName, cryptoAmount, cryptoValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cryptoImage = itemView.findViewById(R.id.crypto_imagen);
            cryptoName = itemView.findViewById(R.id.crypto_nombre);
            cryptoAmount = itemView.findViewById(R.id.crypto_cantidad);
            cryptoValue = itemView.findViewById(R.id.crypto_valor);
        }
    }
}
