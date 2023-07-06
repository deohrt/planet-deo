package com.deohrt.planet.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TambahActivity extends AppCompatActivity {

    private EditText etNama, etpengertian, etukuran, etKarakteristik, etSejarah;

    private Button btnSimpan;

    private String nama, pengertian, ukuran, karakteristik, sejarah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

       etNama = findViewById(R.id.et_nama);
       etpengertian = findViewById(R.id.et_pengertian);
       etukuran = findViewById(R.id.et_ukuran);
       etKarakteristik = findViewById(R.id.et_karakteristik);
       etSejarah = findViewById(R.id.et_sejarah);

       btnSimpan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               nama = etNama.getText().toString();
               pengertian = etpengertian.toString().toString();
               ukuran = etukuran.getText().toString();
               karakteristik = etKarakteristik.toString().toString();
               sejarah = etSejarah.toString().toString();

               if (nama.trim().isEmpty()){
                   etNama.setError("Nama Tidak Boleh Kosong");
               }
               else if (pengertian.trim().isEmpty()){
                   etpengertian.setError("pengertian tidak boleh kosong");
               }
               else if (ukuran.trim().isEmpty()){
                   etukuran.setError("ukuran tidak boleh kosong");
               }
               else if (karakteristik.trim().isEmpty()){
                   etKarakteristik.setError("karakteristik tidak boleh kosong");
               }
               else if (sejarah.trim().isEmpty()){
                   etSejarah.setError("sejarah tidak boleh kosong");
               } else {
                   tambahPlanet();
               }
           }
       });
    }

    private void tambahPlanet(){
        APIRequestData ARD = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(nama, pengertian, ukuran, karakteristik, sejarah);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "kode" + kode + "pesan" + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}