package com.example.uasts;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateRumour extends AppCompatActivity {
    EditText etNama, etClub, etPosisi;
    ImageButton ibPlayer, ibfromClub, ibtoClub;
    ImageView ivDone;
    Uri playerPhotoUri, fromClubPhotoUri, toClubPhotoUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrumour);

        etNama = findViewById(R.id.etNama);
        etClub = findViewById(R.id.etClub);
        etPosisi = findViewById(R.id.etPosisi);
        ibPlayer = findViewById(R.id.ibPlayer);
        ibfromClub = findViewById(R.id.ibfromClub);
        ibtoClub = findViewById(R.id.ibtoClub);
        ivDone = findViewById(R.id.ivDone);


    }
}