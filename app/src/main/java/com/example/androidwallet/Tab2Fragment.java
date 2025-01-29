package com.example.androidwallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidwallet.databinding.Tab2FragmentBinding;

import java.util.Arrays;
import java.util.List;

public class Tab2Fragment extends Fragment {

    private Tab2FragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Tab2FragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Configurar RecyclerView con ViewBinding
        List<String> nfts = Arrays.asList("Bored Ape", "CryptoPunk", "Azuki", "Doodles");
        Adapter myAdapter = new Adapter(nfts);

        binding.recyclerViewNft.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewNft.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
