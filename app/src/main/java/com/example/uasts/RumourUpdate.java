package com.example.uasts;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uasts.api.ApiClient;
import com.example.uasts.api.ApiInterface;
import com.example.uasts.model.updaterumour.UpdateRumour;
import com.example.uasts.others.temporary.TemporaryRumour;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RumourUpdate extends AppCompatActivity {

    EditText etNama, etClub, etPrice, etfromClub;
    ImageButton ibPlayer, ibfromClub, ibtoClub;
    Spinner spDescription, etPosisi;
    ImageView ivDone;
    Uri playerPhotoUri, fromClubPhotoUri, toClubPhotoUri;
    TemporaryRumour temporaryRumour;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updaterumour);

        etNama = findViewById(R.id.etNama);
        etClub = findViewById(R.id.ettoClub);
        etPosisi = findViewById(R.id.etPosisi);
        etPrice = findViewById(R.id.etPrice);
        etfromClub = findViewById(R.id.etfromClub);
        ibPlayer = findViewById(R.id.ibPlayer);
        ibfromClub = findViewById(R.id.ibfromClub);
        ibtoClub = findViewById(R.id.ibtoClub);
        ivDone = findViewById(R.id.ivDone);
        spDescription = findViewById(R.id.spDescription);

        temporaryRumour = new TemporaryRumour(this);

        ArrayAdapter<CharSequence> positionAdapter = ArrayAdapter.createFromResource(this,
                R.array.positions_array, android.R.layout.simple_spinner_item);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etPosisi.setAdapter(positionAdapter);

        ArrayAdapter<CharSequence> descriptionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.description_templates));
        descriptionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDescription.setAdapter(descriptionAdapter);

        spDescription.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        id = temporaryRumour.getRumourId();
        ibPlayer.setOnClickListener(v -> selectImage(1));
        ibfromClub.setOnClickListener(v -> selectImage(2));
        ibtoClub.setOnClickListener(v -> selectImage(3));

        ivDone.setOnClickListener(v -> submitRumour());
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
                    playerPhotoUri = selectedImageUri;
                    break;
                case 2:
                    fromClubPhotoUri = selectedImageUri;
                    break;
                case 3:
                    toClubPhotoUri = selectedImageUri;
                    break;
            }
        }
    }

    private void submitRumour() {

        int rumourId = id;

        String playerName = etNama.getText().toString();
        String clubName = etClub.getText().toString();
        String position = etPosisi.getSelectedItem().toString();
        String price = etPrice.getText().toString();
        String fromclubname = etfromClub.getText().toString();
        String description = spDescription.getSelectedItem().toString();


        if (playerName.isEmpty() || clubName.isEmpty() || position.isEmpty() || price.isEmpty() || fromclubname.isEmpty() || description.isEmpty() || playerPhotoUri == null || fromClubPhotoUri == null || toClubPhotoUri == null) {
            Toast.makeText(this, "Please fill all the fields and select all images", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody playerNameBody = RequestBody.create(MediaType.parse("text/plain"), playerName);
        RequestBody clubNameBody = RequestBody.create(MediaType.parse("text/plain"), clubName);
        RequestBody positionBody = RequestBody.create(MediaType.parse("text/plain"), position);
        RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), price);
        RequestBody fromclubNameBody = RequestBody.create(MediaType.parse("text/plain"), fromclubname);
        RequestBody descriptionBody = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody rumourIdBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(rumourId));

        MultipartBody.Part playerPhotoPart = prepareFilePart("player_photo", playerPhotoUri);
        MultipartBody.Part fromClubPhotoPart = prepareFilePart("club_photo", fromClubPhotoUri);
        MultipartBody.Part toClubPhotoPart = prepareFilePart("fromclub", toClubPhotoUri);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UpdateRumour> call = apiInterface.updateRumour(playerNameBody, playerPhotoPart, positionBody, clubNameBody, fromClubPhotoPart, priceBody, toClubPhotoPart, fromclubNameBody, descriptionBody, rumourIdBody);
        call.enqueue(new Callback<UpdateRumour>() {
            @Override
            public void onResponse(Call<UpdateRumour> call, Response<UpdateRumour> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("RumourUpdate", "Rumour updated successfully!");
                    finish();
                } else {
                    Toast.makeText(RumourUpdate.this, "Failed to update rumour", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateRumour> call, Throwable t) {
                Toast.makeText(RumourUpdate.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
