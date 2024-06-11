package com.example.uasts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.uasts.others.temporary.TemporaryTransfer;

public class DetailTransfer extends AppCompatActivity {
    TextView tvpemainDetail, tvPrice, tvPosition, tvDeskripsi;
    ImageView ivDetail, ivClub;
    ImageButton ibUpdate;
    private TemporaryTransfer temporaryTransfer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtransfer);


        tvpemainDetail = findViewById(R.id.tvpemainDetail);
        tvPosition = findViewById(R.id.tvPosition);
        tvPrice = findViewById(R.id.tvPrice);
        ivClub = findViewById(R.id.ivfromClub);
        ivDetail = findViewById(R.id.ivDetail);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        ibUpdate = findViewById(R.id.ibUpdate);


        temporaryTransfer = new TemporaryTransfer(this);
        String playerName = temporaryTransfer.getPlayerName();
        String playerPhoto = temporaryTransfer.getPlayerPhoto();
        String playerPosition = temporaryTransfer.getPlayerPosition();
        String description = temporaryTransfer.getDescription();
        String transferPrice = temporaryTransfer.getPlayerPrice();
        String clubPhoto = temporaryTransfer.getClubPhoto();
        String clubName = temporaryTransfer.getClubName();
        String beforeclubName = temporaryTransfer.getFromclubname();

        tvpemainDetail.setText(playerName);
        tvPosition.setText(playerPosition);
        tvPrice.setText(transferPrice);
        String filledDescription = replaceHolder(description, playerName, clubName,beforeclubName, transferPrice);
        tvDeskripsi.setText(filledDescription);
        Glide.with(this).load(clubPhoto).into(ivClub);
        Glide.with(this).load(playerPhoto).into(ivDetail);

        ibUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(this, TransferUpdate.class);
            startActivity(intent);
        });

    }
    private String replaceHolder(String template, String playerName, String fromClubName, String beforeclubName, String transferPrice) {
        return template.replace("[Player Name]", playerName)
                .replace("[Before Club Name]", beforeclubName)
                .replace("[Transfer Fee]", transferPrice)
                .replace("[New Club Name]", fromClubName);
    }
}