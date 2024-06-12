package com.example.uasts.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.uasts.SessionManager;
import com.example.uasts.databinding.FragmentHomeBinding;
import com.example.uasts.others.temporary.TemporaryRumour;
import com.example.uasts.others.temporary.TemporaryTransfer;

import java.util.HashMap;

public class HomeFragment extends Fragment {
    TemporaryTransfer temporaryTransfer;
    TemporaryRumour temporaryRumour;
    ImageView ivHome;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SessionManager sessionManager = new SessionManager(getActivity());
        HashMap<String, String> userDetails = sessionManager.getUserDetail();

        String name = userDetails.get(SessionManager.NAME);
        binding.textHome.setText("Welcome Back " + name);

        String profileImageUri = userDetails.get(SessionManager.PROFILE_IMAGE);
        if (profileImageUri != null && !profileImageUri.isEmpty()) {
            Glide.with(this).load(profileImageUri).into(binding.ivHome);
        }

        temporaryTransfer = new TemporaryTransfer(getContext());
        String photoPlayer = temporaryTransfer.getPlayerPhoto();
        String description = temporaryTransfer.getDescription();
        String beforeclubName = temporaryTransfer.getFromclubname();
        String clubName = temporaryTransfer.getClubName();
        String playerName = temporaryTransfer.getPlayerName();
        String transferPrice = temporaryTransfer.getPlayerPrice();
        Glide.with(this).load(photoPlayer).into(binding.ivTransfer);
        String filledDescription = replaceHolder(description, playerName, clubName, beforeclubName, transferPrice);
        binding.tvTransfer.setText(filledDescription);

        temporaryRumour = new TemporaryRumour(getContext());
        String playerNameRumour = temporaryRumour.getPlayerName();
        String playerPhotoRumour = temporaryRumour.getPlayerPhoto();
        String descriptionRumour = temporaryRumour.getDescription();
        String transferPriceRumour = temporaryRumour.getPlayerPrice();
        String fromClubNameRumour = temporaryRumour.getFromclubname();
        String rumourClubNameRumour = temporaryRumour.getRumourclubName();
        Glide.with(this).load(playerPhotoRumour).into(binding.ivRumour);
        String filledDescriptionRumour = replaceHolderRumour(descriptionRumour, playerNameRumour, fromClubNameRumour, rumourClubNameRumour, transferPriceRumour);
        binding.tvRumour.setText(filledDescriptionRumour);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private String replaceHolder(String template, String playerName, String fromClubName, String beforeclubName, String transferPrice) {
        return template.replace("[Player Name]", playerName)
                .replace("[Before Club Name]", beforeclubName)
                .replace("[Transfer Fee]", transferPrice)
                .replace("[New Club Name]", fromClubName);
    }
    private String replaceHolderRumour(String template, String playerName, String fromClubName, String rumourClubName, String price) {
        return template.replace("[Player Name]", playerName)
                .replace("[Current Club Name]", fromClubName)
                .replace("[Transfer Fee]", price)
                .replace("[New Club Name]", rumourClubName);
    }
}
