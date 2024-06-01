package com.example.uasts.ui.rumour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.uasts.databinding.FragmentRumourBinding;

public class RumourFragment extends Fragment {

    private FragmentRumourBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RumourViewModel rumourViewModel =
                new ViewModelProvider(this).get(RumourViewModel.class);

        binding = FragmentRumourBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRumour;
        rumourViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}