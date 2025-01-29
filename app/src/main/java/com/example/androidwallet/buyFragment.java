package com.example.androidwallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidwallet.WalletViewModel;
import com.example.androidwallet.databinding.FragmentBuyBinding;
import java.util.List;

public class buyFragment extends Fragment {
    private FragmentBuyBinding binding;
    private WalletViewModel walletViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBuyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        walletViewModel = new ViewModelProvider(requireActivity()).get(WalletViewModel.class);

        // Observar cambios en la lista de monedas
        walletViewModel.getMonedas().observe(getViewLifecycleOwner(), listaMonedas -> {
            if (listaMonedas != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_spinner_item, listaMonedas);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.monedasSpinnerComprar.setAdapter(adapter);
            }
        });

        // Botón para comprar
        binding.comprarMoneda.setOnClickListener(v -> {
            String monedaSeleccionada = (String) binding.monedasSpinnerComprar.getSelectedItem();
            String cantidad = binding.cantidadMonedaComprar.getText().toString();
            // Lógica para comprar la moneda
        });
    }
}
