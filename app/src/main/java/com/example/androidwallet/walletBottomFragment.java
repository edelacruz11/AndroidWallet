package com.example.androidwallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.androidwallet.databinding.FragmentWalletBottomBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class walletBottomFragment extends Fragment {

    private FragmentWalletBottomBinding binding;
    private WalletViewModel walletViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWalletBottomBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        walletViewModel = new ViewModelProvider(requireActivity()).get(WalletViewModel.class);

        walletViewModel.getMonedas().observe(getViewLifecycleOwner(), lista -> {
            double total = 0.0;
            for (CryptoBalance crypto : lista) {
                total += crypto.getValorEnEuros();
            }
            binding.walletCantidad.setText(String.format("%.2f€", total));
        });

        binding.viewPager.setAdapter(new ViewPagerAdapter(this));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Criptos");
                    break;
                case 1:
                    tab.setText("NFTs");
                    break;
            }
        }).attach();

        binding.btnBuy.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_wallet_to_buy)
        );

        binding.btnSend.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_wallet_to_send)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private static class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return position == 0 ? new Tab1Fragment() : new Tab2Fragment();
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
