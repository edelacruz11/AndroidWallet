package com.example.androidwallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends ListAdapter<Crypto, Adapter.ViewHolder> {

    // Constructor vacío, ya no necesitamos pasar la lista
    protected Adapter() {
        super(DIFF_CALLBACK);
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
        Crypto crypto = getItem(position);  // Usar getItem() para obtener los datos
        holder.cryptoName.setText(crypto.getNombre());
        holder.cryptoAmount.setText(crypto.getCantidad() + " " + crypto.getNombre().substring(0, 3));
        holder.cryptoValue.setText(crypto.getCantidad() * 10 + "€");  // Asegúrate de poner el valor correcto
        holder.cryptoImage.setImageResource(crypto.getImagenResId());
    }

    @Override
    public int getItemCount() {
        return getCurrentList().size();  // Usar getCurrentList() en lugar de data.size()
    }

    // DiffUtil para manejar la diferencia entre las listas
    private static final DiffUtil.ItemCallback<Crypto> DIFF_CALLBACK = new DiffUtil.ItemCallback<Crypto>() {
        @Override
        public boolean areItemsTheSame(@NonNull Crypto oldItem, @NonNull Crypto newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Crypto oldItem, @NonNull Crypto newItem) {
            return oldItem.getCantidad() == newItem.getCantidad();  // Comparar por cantidad también
        }
    };

    // ViewHolder
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
