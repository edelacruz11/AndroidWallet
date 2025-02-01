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

import com.example.androidwallet.databinding.FragmentSendBinding;

import java.util.List;
import java.util.stream.Collectors;

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

        // Cargar criptomonedas en el Spinner
        walletViewModel.getMonedas().observe(getViewLifecycleOwner(), listaMonedas -> {
            if (listaMonedas != null) {
                List<String> nombresMonedas = listaMonedas.stream()
                        .map(CryptoBalance::getNombre) // Usar CryptoBalance
                        .collect(Collectors.toList());

                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_spinner_item, nombresMonedas);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.monedasSpinnerEnviar.setAdapter(adapter);
            }
        });

        // Botón para enviar
        binding.enviarMoneda.setOnClickListener(v -> {
            String monedaSeleccionada = (String) binding.monedasSpinnerEnviar.getSelectedItem();
            String cantidadStr = binding.cantidadMonedaEnviar.getText().toString().trim();
            String walletDestino = binding.otraWalletMonedaEnviar.getText().toString().trim(); // Campo de wallet

            // Validar si la dirección de wallet está vacía
            if (walletDestino.isEmpty()) {
                binding.otraWalletMonedaEnviar.setError("Ingrese una wallet de destino");
                return;
            }

            // Validar cantidad
            if (cantidadStr.isEmpty()) {
                binding.cantidadMonedaEnviar.setError("Ingrese una cantidad");
                return;
            }

            try {
                double cantidad = Double.parseDouble(cantidadStr);
                if (cantidad <= 0) {
                    binding.cantidadMonedaEnviar.setError("Ingrese una cantidad válida");
                    return;
                }

                // Verificar si hay saldo suficiente
                List<CryptoBalance> listaActual = walletViewModel.getMonedas().getValue();
                if (listaActual != null) {
                    for (CryptoBalance crypto : listaActual) {
                        if (crypto.getNombre().equals(monedaSeleccionada)) {
                            if (crypto.getCantidad() >= cantidad) {
                                walletViewModel.enviarCrypto(monedaSeleccionada, cantidad);
                                NavController navController = Navigation.findNavController(v);
                                navController.popBackStack(); // Volver atrás tras enviar
                            } else {
                                binding.cantidadMonedaEnviar.setError("Saldo insuficiente");
                            }
                            break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                binding.cantidadMonedaEnviar.setError("Ingrese un número válido");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
