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

import com.example.androidwallet.WalletViewModel;
import com.example.androidwallet.databinding.Tab1FragmentBinding;

import java.util.Arrays;
import java.util.List;

public class Tab1Fragment extends Fragment {
    private Tab1FragmentBinding binding;
    private WalletViewModel walletViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = Tab1FragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Instanciar ViewModel compartido
        walletViewModel = new ViewModelProvider(requireActivity()).get(WalletViewModel.class);

        // Simulación de lista de monedas (debería venir del RecyclerView)
        List<String> listaMonedas = Arrays.asList("Bitcoin", "Ethereum", "Cardano", "Solana");

        // Guardar lista en WalletViewModel
        walletViewModel.setMonedas(listaMonedas);

        // Configurar RecyclerView
        Adapter adapter = new Adapter(listaMonedas);
        binding.recyclerViewCoins.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCoins.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
