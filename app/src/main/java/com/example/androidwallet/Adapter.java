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
    private List<CryptoBalance> data;

    public Adapter(List<CryptoBalance> data) {
        this.data = data;
    }

    public void setData(List<CryptoBalance> newData) {
        this.data = newData;
        notifyDataSetChanged();
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
        CryptoBalance cryptoBalance = data.get(position);
        holder.cryptoName.setText(cryptoBalance.getNombre());
        holder.cryptoAmount.setText(cryptoBalance.getCantidad() + " " + cryptoBalance.getNombre().substring(0, 3));
        holder.cryptoValue.setText(cryptoBalance.getValorEnEuros() + "€");
        holder.cryptoImage.setImageResource(getImageResourceId(cryptoBalance.getNombre()));
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

    private int getImageResourceId(String nombre) {
        switch (nombre) {
            case "Bitcoin":
                return R.drawable.bitcoin;
            case "Ethereum":
                return R.drawable.ethereum;
            case "Cardano":
                return R.drawable.cardano;
            case "Solana":
                return R.drawable.solana;
            default:
                return R.drawable.ic_launcher_foreground;
        }
    }
}
