package com.example.uasts;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.uasts.others.temporary.TemporaryRumour;

public class DetailRumour extends AppCompatActivity {

    TextView tvPosition, tvpemainDetail, tvPrice;
    ImageView ivDetail;
    TemporaryRumour temporaryRumour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailrumour);

        tvPosition = findViewById(R.id.tvPosition);
        tvpemainDetail = findViewById(R.id.tvpemainDetail);
        tvPrice = findViewById(R.id.tvPrice);
        ivDetail = findViewById(R.id.ivDetail);

        temporaryRumour = new TemporaryRumour(this);
        String playerName = temporaryRumour.getPlayerName();
        String playerPhoto = temporaryRumour.getPlayerPhoto();
        String playerPosition = temporaryRumour.getPlayerPosition();
        String transferprice = temporaryRumour.getPlayerPrice();

        tvpemainDetail.setText(playerName);
        tvPosition.setText(playerPosition);
        tvPrice.setText(transferprice);
        Glide.with(this).load(playerPhoto).into(ivDetail);

    }
}
