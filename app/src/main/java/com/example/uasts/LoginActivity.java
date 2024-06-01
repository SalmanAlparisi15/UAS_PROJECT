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
import com.example.uasts.model.login.LoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername, etPassword;
    CardView cvRegister;
    Button btnLogin;
    String username, password;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        cvRegister = findViewById(R.id.cvRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
        cvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnLogin) {
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            login(username, password);
        } else if (id == R.id.cvRegister) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    private void login(String username, String password) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.loginResponse(username, password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
                    LoginData loginData = response.body().getLoginData();
                    sessionManager.createLoginSession(loginData);
                    sessionManager.setIsAdmin(loginData.isIsAdmin());
                    Toast.makeText(LoginActivity.this, response.body().getLoginData().getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
