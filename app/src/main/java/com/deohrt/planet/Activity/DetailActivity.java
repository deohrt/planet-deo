package com.deohrt.planet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.deohrt.planet.R;

public class DetailActivity extends AppCompatActivity {

    private String id, nama, pengertian, ukuran, karakteristik, sejarah;

    private TextView tvId, tvnama, tvPengertian, tvukuran, tvKarakteristik, tvSejarah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent ambil = getIntent();
        id = ambil.getStringExtra("id");
         nama = ambil.getStringExtra("Nama");
         pengertian = ambil.getStringExtra("Pengertian");
         ukuran = ambil.getStringExtra("ukuran");
         karakteristik = ambil.getStringExtra("karakteristik");
         sejarah = ambil.getStringExtra("Sejarah");

         tvId = findViewById(R.id.tv_id);
         tvnama = findViewById(R.id.tv_nama);
         tvPengertian = findViewById(R.id.tv_pengertian);
         tvukuran = findViewById(R.id.tv_ukuran);
         tvKarakteristik = findViewById(R.id.tv_karakteristik);
         tvSejarah = findViewById(R.id.tv_sejarah);

         tvId.setText(id);
         tvnama.setText(nama);
         tvPengertian.setText(pengertian);
         tvukuran.setText(ukuran);
         tvKarakteristik.setText(karakteristik);
         tvSejarah.setText(sejarah);
    }
}