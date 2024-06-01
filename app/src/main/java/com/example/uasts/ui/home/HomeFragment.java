package com.example.uasts.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.uasts.SessionManager;
import com.example.uasts.databinding.FragmentHomeBinding;

import java.util.HashMap;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SessionManager sessionManager = new SessionManager(getActivity());
        HashMap<String, String> userDetails = sessionManager.getUserDetail();

        String name = userDetails.get(SessionManager.NAME);
        binding.textHome.setText("Welcome, " + name);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
