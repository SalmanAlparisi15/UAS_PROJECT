package com.example.uasts;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.uasts.others.temporary.TemporaryTransfer;

public class DetailTransfer extends AppCompatActivity {
    TextView tvpemainDetail, tvPrice, tvPosition;
    ImageView ivDetail;
    private TemporaryTransfer temporaryTransfer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtransfer);


        tvpemainDetail = findViewById(R.id.tvpemainDetail);
        tvPosition = findViewById(R.id.tvPosition);
        tvPrice = findViewById(R.id.tvPrice);
        ivDetail = findViewById(R.id.ivDetail);

        temporaryTransfer = new TemporaryTransfer(this);
        String playerName = temporaryTransfer.getPlayerName();
        String playerPhoto = temporaryTransfer.getPlayerPhoto();
        String playerPosition = temporaryTransfer.getPlayerPosition();
        String transferprice = temporaryTransfer.getPlayerPrice();

        tvpemainDetail.setText(playerName);
        tvPosition.setText(playerPosition);
        tvPrice.setText(transferprice);
        Glide.with(this).load(playerPhoto).into(ivDetail);

    }
}