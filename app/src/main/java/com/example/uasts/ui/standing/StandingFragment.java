package com.example.uasts.ui.standing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.uasts.databinding.FragmentStandingBinding;

public class StandingFragment extends Fragment {

    private FragmentStandingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StandingViewModel standingViewModel =
                new ViewModelProvider(this).get(StandingViewModel.class);

        binding = FragmentStandingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textStanding;
        standingViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}