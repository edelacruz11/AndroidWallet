package com.example.androidwallet;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidwallet.databinding.NftListBinding;

import java.util.List;

public class NftAdapter extends RecyclerView.Adapter<NftAdapter.ViewHolder> {
    private List<NFT> nfts;

    public NftAdapter(List<NFT> nfts) {
        this.nfts = nfts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NftListBinding binding = NftListBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NFT nft = nfts.get(position);

        holder.binding.nftNombre.setText(nft.getNombre());
        holder.binding.nftImagen.setImageResource(nft.getImagenResId());

        holder.binding.getRoot().setOnClickListener(v -> {
            DetalleNFTFragment detalleFragment = DetalleNFTFragment.newInstance(nft);

            Navigation.findNavController(v).navigate(
                    R.id.action_wallet_to_detalleNFTFragment,
                    detalleFragment.getArguments()
            );
        });
    }

    @Override
    public int getItemCount() {
        return nfts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        NftListBinding binding;

        public ViewHolder(@NonNull NftListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
