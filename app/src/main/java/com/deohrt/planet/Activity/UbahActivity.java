package com.deohrt.planet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deohrt.planet.API.APIRequestData;
import com.deohrt.planet.API.RetroServer;
import com.deohrt.planet.Model.ModelResponse;
import com.deohrt.planet.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {

    private String yId, yNama, yPengertian, yUkuran, yKarakteristik, ySejarah;

    private EditText editText, etNama, etPengertian, etUkuran, etkarakteristik, etsejarah;

    private Button btnUbah;

    private String nama, pengertian, ukuran ,karakteristik, sejarah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);


        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yPengertian = ambil.getStringExtra("xpengertian");
        yUkuran = ambil.getStringExtra("xUkuran");
        yKarakteristik = ambil.getStringExtra("xkarakteristik");
        ySejarah = ambil.getStringExtra("xsejarah");

        etNama = findViewById(R.id.et_nama);
        etPengertian = findViewById(R.id.et_pengertian);
        etUkuran = findViewById(R.id.et_ukuran);
        etkarakteristik = findViewById(R.id.et_karakteristik);
        etsejarah = findViewById(R.id.et_sejarah);
        btnUbah = findViewById(R.id.btn_ubah);

        etNama.setText(yNama);
        etPengertian.setText(yPengertian);
        etUkuran.setText(yUkuran);
        etkarakteristik.setText(yKarakteristik);
        etsejarah.setText(ySejarah);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                pengertian = etPengertian.getText().toString();
                ukuran = etUkuran.getText().toString();
                karakteristik = etkarakteristik.getText().toString();
                sejarah = etsejarah.getText().toString();


                if (nama.trim().isEmpty()) {
                    etNama.setError("Nama tidak boleh kosong!");
                } else if (pengertian.trim().isEmpty()) {
                    etPengertian.setError("pengertian tidak boleh kosong!");
                } else if (ukuran.trim().isEmpty()) {
                    etUkuran.setError("Ukuran tidak boleh kosong!");
                } else if (karakteristik.trim().isEmpty()) {
                    etUkuran.setError("karakteristik tidak boleh kosong!");
                } else if (sejarah.trim().isEmpty()) {
                    etsejarah.setError("sejarah tidak boleh kosong!");
                } else {
                    ubahPlanet();
                }

            }
        });
    }
        private void ubahPlanet(){
            APIRequestData ARD = RetroServer.koneksiRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = ARD.ardUpdate(yId, yNama, yPengertian, yUkuran, yKarakteristik, ySejarah);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(UbahActivity.this, "Kode: " + kode + ", Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();


                }
            });
        }


    }