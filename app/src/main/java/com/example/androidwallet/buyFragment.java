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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidwallet.databinding.FragmentBuyBinding;

import java.util.List;
import java.util.stream.Collectors;

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

        // Cargar las criptomonedas en el Spinner
        walletViewModel.getMonedas().observe(getViewLifecycleOwner(), listaMonedas -> {
            if (listaMonedas != null) {
                List<String> nombresMonedas = listaMonedas.stream()
                        .map(CryptoBalance::getNombre)
                        .collect(Collectors.toList());

                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_spinner_item, nombresMonedas);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.monedasSpinnerComprar.setAdapter(adapter);
            }
        });

        // Botón para comprar
        binding.comprarMoneda.setOnClickListener(v -> {
            String monedaSeleccionada = (String) binding.monedasSpinnerComprar.getSelectedItem();
            String cantidadStr = binding.cantidadMonedaComprar.getText().toString().trim();

            if (cantidadStr.isEmpty()) {
                binding.cantidadMonedaComprar.setError("Ingrese una cantidad");
                return;
            }
            try {
                double cantidad = Double.parseDouble(cantidadStr);
                if (cantidad <= 0) {
                    binding.cantidadMonedaComprar.setError("Ingrese una cantidad válida");
                    return;
                }

                walletViewModel.comprarCrypto(monedaSeleccionada, cantidad);
                NavController navController = Navigation.findNavController(v);
                navController.popBackStack();
            } catch (NumberFormatException e) {
                binding.cantidadMonedaComprar.setError("Ingrese un número válido");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
