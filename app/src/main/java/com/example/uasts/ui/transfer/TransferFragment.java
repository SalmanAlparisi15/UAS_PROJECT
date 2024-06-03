package com.example.uasts.ui.transfer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasts.TransferAdapter;
import com.example.uasts.api.ApiClient;
import com.example.uasts.api.ApiInterface;
import com.example.uasts.model.transferfile.TransferFile;
import com.example.uasts.model.transferfile.TransferFileData;
import com.example.uasts.databinding.FragmentTransferBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferFragment extends Fragment {

    private FragmentTransferBinding binding;
    private RecyclerView recyclerView;
    private TransferAdapter transferAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTransferBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.rvTransfer;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchTransfers();

        return root;
    }

    private void fetchTransfers() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TransferFile> call = apiInterface.getTransferFile();
        call.enqueue(new Callback<TransferFile>() {
            @Override
            public void onResponse(Call<TransferFile> call, Response<TransferFile> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TransferFile transferFile = response.body();
                    List<TransferFileData> transfers = transferFile.getData();
                    transferAdapter = new TransferAdapter(transfers, getContext());
                    recyclerView.setAdapter(transferAdapter);
                } else {
                    showErrorToast("Failed to retrieve data. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<TransferFile> call, Throwable t) {
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
