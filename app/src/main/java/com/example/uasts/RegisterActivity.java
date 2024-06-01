package com.example.uasts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.uasts.api.ApiClient;
import com.example.uasts.api.ApiInterface;
import com.example.uasts.model.login.Login;
import com.example.uasts.model.register.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etregUsername, etregName, etregPassword;
    Button btnRegister;
    CardView cvLogin;
    String username, name, password;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etregUsername = findViewById(R.id.etregUsername);
        etregName = findViewById(R.id.etregName);
        etregPassword = findViewById(R.id.etregPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        cvLogin = findViewById(R.id.cvregSignin);
        cvLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnRegister) {
            String username = etregUsername.getText().toString();
            String name = etregName.getText().toString();
            String password = etregPassword.getText().toString();
            register(username,name,password);

        } else if (id == R.id.cvregSignin) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }
    }

    private void register(String username, String name, String password) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> registerCall = apiInterface.registerResponse(username, name, password);
        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

                @Override
                public void onFailure(Call<Register> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }

        });
    }

}