package com.deohrt.planet.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deohrt.planet.API.APIRequestData;
import com.deohrt.planet.API.RetroServer;
import com.deohrt.planet.Activity.DetailActivity;
import com.deohrt.planet.Activity.MainActivity;
import com.deohrt.planet.Activity.UbahActivity;
import com.deohrt.planet.Model.ModelPlanet;
import com.deohrt.planet.Model.ModelResponse;
import com.deohrt.planet.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPlanet extends RecyclerView.Adapter<AdapterPlanet.VHPlanet> {
    private Context ctx;
    private List<ModelPlanet> listPlanet;

    public AdapterPlanet(Context ctx, List<ModelPlanet> listPlanet) {
        this.ctx = ctx;
        this.listPlanet = listPlanet;
    }

    @NonNull
    @Override
    public AdapterPlanet.VHPlanet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_planet, parent, false);
        return new VHPlanet(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPlanet.VHPlanet holder, int position) {
        ModelPlanet MP = listPlanet.get(position);
        holder.tvId.setText(MP.getId());
        holder.tvNama.setText(MP.getNama());
        holder.tvPengertian.setText(MP.getPengertian());
        holder.tvukuran.setText(MP.getUkuran());
        holder.tvKarakteristik.setText(MP.getKarakteristik());
        holder.tvSejarah.setText(MP.getSejarah());
    }

    @Override
    public int getItemCount() {
        return listPlanet.size();
    }

    public class VHPlanet extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, tvPengertian, tvukuran, tvKarakteristik, tvSejarah;

        public VHPlanet(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvPengertian = itemView.findViewById(R.id.tv_pengertian);
            tvukuran = itemView.findViewById(R.id.tv_ukuran);
            tvKarakteristik = itemView.findViewById(R.id.tv_karakteristik);
            tvSejarah = itemView.findViewById(R.id.tv_sejarah);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent pindah = new Intent(ctx, DetailActivity.class);
                    pindah.putExtra("xId", tvId.getText().toString());
                    pindah.putExtra("xNama", tvNama.getText().toString());
                    pindah.putExtra("xPengertian", tvPengertian.getText().toString());
                    pindah.putExtra("xUkuran", tvukuran.getText().toString());
                    pindah.putExtra("xKarakteristik", tvKarakteristik.getText().toString());
                    pindah.putExtra("xSejarah", tvSejarah.getText().toString());
                    ctx.startActivity(pindah);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("perhatian");
                    pesan.setMessage("operasi apa yang akan dilakukan");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapusPlanet(tvId.getText().toString());
                            dialog.dismiss();
                        }
                    });

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent pindah = new Intent(ctx, UbahActivity.class);
                            pindah.putExtra("Id", tvId.getText().toString());
                            pindah.putExtra("Nama", tvNama.getText().toString());
                            pindah.putExtra("Pengertian", tvPengertian.getText().toString());
                            pindah.putExtra("Ukuran", tvukuran.getText().toString());
                            pindah.putExtra("karakteristik", tvKarakteristik.getText().toString());
                            pindah.putExtra("Sejarah", tvSejarah.getText().toString());
                            ctx.startActivity(pindah);
                        }
                    });
                    pesan.show();
                    return false;
                }
            });
        }

        private void hapusPlanet(String idPlanet) {
            APIRequestData ARD = RetroServer.koneksiRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = ARD.ardDelete(idPlanet);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode: " + kode + ",pesan:" + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity)ctx).retrievePlanet();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
