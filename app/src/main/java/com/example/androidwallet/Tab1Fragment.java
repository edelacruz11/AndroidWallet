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

        // Configurar RecyclerView
        Adapter adapter = new Adapter();
        binding.recyclerViewCoins.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCoins.setAdapter(adapter);

        // Observar los datos desde el ViewModel
        walletViewModel.getMonedas().observe(getViewLifecycleOwner(), cryptos -> {
            // Cuando los datos cambian, actualizar el adapter
            adapter.submitList(cryptos);  // Actualizar la lista de items en el adapter
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
