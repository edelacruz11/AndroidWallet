package com.example.androidwallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidwallet.databinding.FragmentDetalleNftBinding;

public class DetalleNFTFragment extends Fragment {

    private static final String ARG_NFT = "nft";
    private NFT nft;
    private FragmentDetalleNftBinding binding;

    public DetalleNFTFragment() {
    }

    public static DetalleNFTFragment newInstance(NFT nft) {
        DetalleNFTFragment fragment = new DetalleNFTFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NFT, nft);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nft = getArguments().getParcelable(ARG_NFT);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetalleNftBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (nft != null) {
            binding.imagenDetalle.setImageResource(nft.getImagenResId());
            binding.nombreDetalle.setText(nft.getNombre());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
