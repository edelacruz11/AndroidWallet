package com.example.androidwallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidwallet.databinding.Tab1FragmentBinding;

import java.util.Arrays;
import java.util.List;

public class Tab1Fragment extends Fragment {

    private Tab1FragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Tab1FragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Configurar RecyclerView con ViewBinding
        List<String> coins = Arrays.asList("Bitcoin", "Ethereum", "Solana", "Cardano");
        Adapter myAdapter = new Adapter(coins);

        binding.recyclerViewCoins.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCoins.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
