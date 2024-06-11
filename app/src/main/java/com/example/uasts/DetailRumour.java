package com.example.uasts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.uasts.others.temporary.TemporaryRumour;

public class DetailRumour extends AppCompatActivity {

    TextView tvPosition, tvpemainDetail, tvPrice, tvDeskripsi;
    ImageView ivDetail, ivfromClub;
    TemporaryRumour temporaryRumour;
    ImageButton ibUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailrumour);

        tvPosition = findViewById(R.id.tvPosition);
        tvpemainDetail = findViewById(R.id.tvpemainDetail);
        tvPrice = findViewById(R.id.tvPrice);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        ivfromClub = findViewById(R.id.ivfromClub);
        ivDetail = findViewById(R.id.ivDetail);
        ibUpdate = findViewById(R.id.ibUpdate);

        temporaryRumour = new TemporaryRumour(this);
        String playerName = temporaryRumour.getPlayerName();
        String playerPhoto = temporaryRumour.getPlayerPhoto();
        String playerPosition = temporaryRumour.getPlayerPosition();
        String description = temporaryRumour.getDescription();
        String transferPrice = temporaryRumour.getPlayerPrice();
        String fromClubPlayer = temporaryRumour.getRumourclubPhoto();
        String fromClubName = temporaryRumour.getFromclubname();
        String rumourClubName = temporaryRumour.getRumourclubName();

        tvpemainDetail.setText(playerName);
        tvPosition.setText(playerPosition);
        tvPrice.setText(transferPrice);
        String filledDescription = replaceHolder(description, playerName, fromClubName, rumourClubName, transferPrice);
        tvDeskripsi.setText(filledDescription);
        Glide.with(this).load(playerPhoto).into(ivDetail);
        Glide.with(this).load(fromClubPlayer).into(ivfromClub);

        ibUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(this, RumourUpdate.class);
            startActivity(intent);
        });
    }

    private String replaceHolder(String template, String playerName, String fromClubName, String rumourClubName, String price) {
        return template.replace("[Player Name]", playerName)
                .replace("[Current Club Name]", fromClubName)
                .replace("[Transfer Fee]", price)
                .replace("[New Club Name]", rumourClubName);
    }

}
