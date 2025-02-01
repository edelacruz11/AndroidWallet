package com.example.androidwallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidwallet.databinding.Tab1FragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class Tab1Fragment extends Fragment {
    private Tab1FragmentBinding binding;
    private WalletViewModel walletViewModel;
    private Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = Tab1FragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        walletViewModel = new ViewModelProvider(requireActivity()).get(WalletViewModel.class);

        // Configurar RecyclerView
        adapter = new Adapter(new ArrayList<>());
        binding.recyclerViewCoins.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCoins.setAdapter(adapter);

        // Observar cambios en la lista de monedas
        walletViewModel.getMonedas().observe(getViewLifecycleOwner(), listaMonedas -> {
            // Pasar la lista de CryptoBalance al adaptader
            adapter.setData(listaMonedas);
            adapter.notifyDataSetChanged();
        });
    }
}
