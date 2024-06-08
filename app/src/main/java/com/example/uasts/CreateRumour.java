package com.example.uasts;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uasts.api.ApiClient;
import com.example.uasts.api.ApiInterface;
import com.example.uasts.model.postrumour.PostRumour;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRumour extends AppCompatActivity {
    EditText etNama, etClub, etPosisi, etPrice;
    ImageButton ibPlayer, ibfromClub, ibtoClub;
    ImageView ivDone;
    Uri playerPhotoUri, fromClubPhotoUri, toClubPhotoUri;

    private static final String TAG = "CreateRumour";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrumour);

        etNama = findViewById(R.id.etNama);
        etClub = findViewById(R.id.etClub);
        etPosisi = findViewById(R.id.etPosisi);
        etPrice = findViewById(R.id.etPrice);
        ibPlayer = findViewById(R.id.ibPlayer);
        ibfromClub = findViewById(R.id.ibfromClub);
        ibtoClub = findViewById(R.id.ibtoClub);
        ivDone = findViewById(R.id.ivDone);

        ibPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(1);
            }
        });

        ibfromClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(2);
            }
        });

        ibtoClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(3);
            }
        });

        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRumour();
            }
        });
    }

    private void selectImage(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            switch (requestCode) {
                case 1:
                    playerPhotoUri =selectedImageUri;
                    break;
                case 2:
                    fromClubPhotoUri =selectedImageUri;
                    break;
                case 3:
                    toClubPhotoUri =selectedImageUri;
                    break;
            }
        }
    }

    private void submitRumour() {
        String playerName = etNama.getText().toString();
        String clubName = etClub.getText().toString();
        String position = etPosisi.getText().toString();
        String price = etPrice.getText().toString();

        if (playerName.isEmpty() || clubName.isEmpty() || position.isEmpty() || price.isEmpty() || playerPhotoUri == null || fromClubPhotoUri == null || toClubPhotoUri == null) {
            Toast.makeText(this, "Please fill all the fields and select all images", Toast.LENGTH_SHORT).show();
            return;
        }


        RequestBody playerNameBody = RequestBody.create(MediaType.parse("text/plain"), playerName);
        RequestBody clubNameBody = RequestBody.create(MediaType.parse("text/plain"), clubName);
        RequestBody positionBody = RequestBody.create(MediaType.parse("text/plain"), position);
        RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), price);

        MultipartBody.Part playerPhotoPart = prepareFilePart("player_photo", playerPhotoUri);
        MultipartBody.Part fromClubPhotoPart = prepareFilePart("club_photo", fromClubPhotoUri);
        MultipartBody.Part toClubPhotoPart = prepareFilePart("fromclub", toClubPhotoUri);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<PostRumour> call = apiInterface.postRumour(playerNameBody, playerPhotoPart, positionBody, clubNameBody, fromClubPhotoPart, priceBody, toClubPhotoPart);

        call.enqueue(new Callback<PostRumour>() {
            @Override
            public void onResponse(Call<PostRumour> call, Response<PostRumour> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreateRumour.this, "Rumour posted successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateRumour.this, "Failed to post rumour", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostRumour> call, Throwable t) {
                Toast.makeText(CreateRumour.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = new File(getPath(fileUri));
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        return null;
    }
}
