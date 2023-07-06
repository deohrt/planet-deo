package com.deohrt.planet.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.deohrt.planet.API.APIRequestData;
import com.deohrt.planet.API.RetroServer;
import com.deohrt.planet.Adapter.AdapterPlanet;
import com.deohrt.planet.Model.ModelPlanet;
import com.deohrt.planet.Model.ModelResponse;
import com.deohrt.planet.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvPlanet;
    private FloatingActionButton fabTambah;
    private ProgressBar pbPlanet;
    private  RecyclerView.Adapter ardPlanet;
    private  RecyclerView.LayoutManager lmPlanet;
    private List<ModelPlanet> planetList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPlanet = findViewById(R.id.rv_planet);
        fabTambah = findViewById(R.id.fab_tambah);
        pbPlanet = findViewById(R.id.pb_planet);

        lmPlanet = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false);
        rvPlanet.setLayoutManager(lmPlanet);

        fabTambah.setOnClickListener(new View.OnClickListener() {
           @Override
           public void  onClick(View view){
               startActivity(new Intent(MainActivity.this, TambahActivity.class));
           }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrievePlanet();
    }

    public void retrievePlanet(){
        pbPlanet.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardRetieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                planetList = response.body().getData();

                ardPlanet = new AdapterPlanet( MainActivity.this, planetList);
                rvPlanet.setAdapter(ardPlanet);
                ardPlanet.notifyDataSetChanged();

                pbPlanet.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {

                Toast.makeText( MainActivity.this, "Galal Menghubungi Server!", Toast.LENGTH_SHORT);
                pbPlanet.setVisibility(View.GONE);

            }
        });
    }
}