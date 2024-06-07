package com.example.uasts.ui.rumour;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasts.CreateRumour;
import com.example.uasts.R;
import com.example.uasts.SessionManager;
import com.example.uasts.others.adapter.RumourAdapter;
import com.example.uasts.api.ApiClient;
import com.example.uasts.api.ApiInterface;
import com.example.uasts.model.rumourfile.RumourFile;
import com.example.uasts.model.rumourfile.RumourFileData;
import com.example.uasts.databinding.FragmentRumourBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RumourFragment extends Fragment {

    private FragmentRumourBinding binding;
    private RecyclerView recyclerView;
    private RumourAdapter rumourAdapter;
    private SessionManager sessionManager;
    private ImageView ibCreate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRumourBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.rvRumuor;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        sessionManager = new SessionManager(getContext());
        ibCreate = binding.ibCreate;

        if (!sessionManager.isAdmin()) {
            ibCreate.setVisibility(View.GONE);

        } else {
            ibCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CreateRumour.class);
                    startActivity(intent);
                }
            });

        }



        fetchRumours();

        return root;
    }

    private void fetchRumours() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<RumourFile> call = apiInterface.getRumourFile();
        call.enqueue(new Callback<RumourFile>() {
            @Override
            public void onResponse(Call<RumourFile> call, Response<RumourFile> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RumourFile RumourFile = response.body();
                    List<RumourFileData> transfers = RumourFile.getData();
                    rumourAdapter = new RumourAdapter(transfers, getContext());
                    recyclerView.setAdapter(rumourAdapter);
                } else {
                    showErrorToast("Failed to retrieve data. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<RumourFile> call, Throwable t) {
                showErrorToast("Network error. Please check your connection.");
            }
        });
    }


    private void showErrorToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
