package com.example.uasts.ui.profil;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.uasts.LoginActivity;
import com.example.uasts.SessionManager;
import com.example.uasts.api.ApiClient;
import com.example.uasts.api.ApiInterface;
import com.example.uasts.databinding.FragmentProfilBinding;
import com.example.uasts.model.delete.Delete;
import com.example.uasts.model.logout.Logout;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private FragmentProfilBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SessionManager sessionManager = new SessionManager(getActivity());
        HashMap<String, String> userDetails = sessionManager.getUserDetail();

        String username = userDetails.get(SessionManager.USERNAME);
        binding.textProfil.setText(username);

        boolean isAdmin = Boolean.parseBoolean(userDetails.get(SessionManager.IS_ADMIN));
        String statusText = isAdmin ? "Admin" : "User";
        String profileImageUri = userDetails.get(SessionManager.PROFILE_IMAGE);
        if (profileImageUri != null && !profileImageUri.isEmpty()) {
            Glide.with(this).load(profileImageUri).into(binding.ivProfil);
        }
        binding.tvstatusProfil.setText(statusText);

        binding.tvkeluarProfil.setOnClickListener(v -> {
            logoutAkun(username);
        });

        binding.tvhapusProfil.setOnClickListener(v -> {
            hapusAkun(username);
        });

        binding.tvfotoProfil.setOnClickListener(v -> {
            openGallery();

        });

        return root;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                Glide.with(this).load(bitmap).into(binding.ivProfil);
                SessionManager sessionManager = new SessionManager(getActivity());
                sessionManager.saveProfileImage(selectedImageUri.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void hapusAkun(String username) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Delete> call = apiInterface.deleteResponse(username);
        call.enqueue(new Callback<Delete>() {
            @Override
            public void onResponse(Call<Delete> call, Response<Delete> response) {
                if (response.body() != null && response.body().isStatus()) {
                    Toast.makeText(getActivity(), "Akun Anda Telah Dihapus", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Gagal Menghapus Akun", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Delete> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghapus Akun: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logoutAkun(String username) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Logout> logoutCall = apiInterface.logoutResponse(username);
        logoutCall.enqueue(new Callback<Logout>() {
            @Override
            public void onResponse(Call<Logout> call, Response<Logout> response) {
                if (response.body() != null && response.body().isStatus()) {
                    Toast.makeText(getActivity(), "Anda Telah Logout", Toast.LENGTH_SHORT).show();
                    SessionManager sessionManager = new SessionManager(getActivity());
                    sessionManager.LogoutSession();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Gagal Logout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Logout> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Logout: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
