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
import com.example.androidwallet.databinding.FragmentSendBinding;
import java.util.List;

public class sendFragment extends Fragment {
    private FragmentSendBinding binding;
    private WalletViewModel walletViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSendBinding.inflate(inflater, container, false);
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
                binding.monedasSpinnerEnviar.setAdapter(adapter);
            }
        });

        // Botón para enviar
        binding.enviarMoneda.setOnClickListener(v -> {
            String monedaSeleccionada = (String) binding.monedasSpinnerEnviar.getSelectedItem();
            String cantidad = binding.cantidadMonedaEnviar.getText().toString();
            // Lógica para enviar la moneda
        });
    }
}
