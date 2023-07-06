package com.deohrt.planet.Model;

import java.util.List;

public class ModelResponse { private String kode, pesan;
    private List<ModelPlanet> data;

    public String getKode(){
        return kode;
    }

    public String getPesan(){
        return pesan;
    }

    public List<ModelPlanet> getData(){
        return data;
    }
}
