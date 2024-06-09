package com.example.uasts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.uasts.others.temporary.TemporaryRumour;

public class DetailRumour extends AppCompatActivity {

    TextView tvPosition, tvpemainDetail, tvPrice, tvDeskripsi;
    ImageView ivDetail, ivfromClub;
    TemporaryRumour temporaryRumour;

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

        temporaryRumour = new TemporaryRumour(this);
        String playerName = temporaryRumour.getPlayerName();
        String playerPhoto = temporaryRumour.getPlayerPhoto();
        String playerPosition = temporaryRumour.getPlayerPosition();
        String description = temporaryRumour.getDescription();
        String transferPrice = temporaryRumour.getPlayerPrice();
        String fromClubPlayer = temporaryRumour.getFromClub();
        String fromClubName = temporaryRumour.getFromclubname();

        tvpemainDetail.setText(playerName);
        tvPosition.setText(playerPosition);
        tvPrice.setText(transferPrice);
        String filledDescription = replaceHolder(description, playerName, fromClubName, transferPrice);
        tvDeskripsi.setText(filledDescription);
        Glide.with(this).load(playerPhoto).into(ivDetail);
        Glide.with(this).load(fromClubPlayer).into(ivfromClub);
    }

    private String replaceHolder(String template, String playerName, String fromClubName, String price) {
        return template.replace("[Player Name]", playerName)
                .replace("[New Club Name]", fromClubName)
                .replace("[Transfer Fee]", price);
    }
}
