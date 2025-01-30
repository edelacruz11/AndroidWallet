package com.example.androidwallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NftAdapter extends RecyclerView.Adapter<NftAdapter.ViewHolder> {
    private List<NFT> nfts;

    public NftAdapter(List<NFT> nfts) {
        this.nfts = nfts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nft_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NFT nft = nfts.get(position);
        holder.textView.setText(nft.getNombre());
        holder.imageView.setImageResource(nft.getImagenResId());
    }

    @Override
    public int getItemCount() {
        return nfts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.nft_nombre);
            imageView = itemView.findViewById(R.id.nft_imagen);
        }
    }
}
