package com.example.androidwallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidwallet.databinding.Tab2FragmentBinding;

import java.util.Arrays;
import java.util.List;

public class Tab2Fragment extends Fragment {
    private Tab2FragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Tab2FragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Lista de NFT con imágenes de ejemplo
        List<NFT> nfts = Arrays.asList(
                new NFT("Bored Ape", R.drawable.bored_ape),
                new NFT("CryptoPunk", R.drawable.cryptopunk),
                new NFT("Azuki", R.drawable.azuki),
                new NFT("Doodles", R.drawable.doodles),
                new NFT("Pudgy Penguins", R.drawable.pudgypenguins)
        );

        NftAdapter adapter = new NftAdapter(nfts);
        binding.recyclerViewNft.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recyclerViewNft.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
