package com.example.androidwallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class sendFragment extends Fragment {
    private WalletViewModel walletViewModel;
    private Spinner spinnerMonedas;
    private EditText cantidadInput;
    private Button btnEnviar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send, container, false);

        walletViewModel = new ViewModelProvider(this).get(WalletViewModel.class);

        spinnerMonedas = root.findViewById(R.id.monedas_spinner_enviar);
        cantidadInput = root.findViewById(R.id.cantidad_moneda_enviar);
        btnEnviar = root.findViewById(R.id.enviar_moneda);

        btnEnviar.setOnClickListener(v -> enviarCrypto());

        return root;
    }

    private void enviarCrypto() {
        String nombreMoneda = spinnerMonedas.getSelectedItem().toString();
        String cantidadStr = cantidadInput.getText().toString();

        if (cantidadStr.isEmpty()) {
            Toast.makeText(getContext(), "Introduce una cantidad", Toast.LENGTH_SHORT).show();
            return;
        }

        double cantidad = Double.parseDouble(cantidadStr);

        // Enviar la criptomoneda
        boolean success = walletViewModel.sendCrypto(nombreMoneda, cantidad);
        if (success) {
            Toast.makeText(getContext(), "Envío realizado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Saldo insuficiente", Toast.LENGTH_SHORT).show();
        }
    }
}
