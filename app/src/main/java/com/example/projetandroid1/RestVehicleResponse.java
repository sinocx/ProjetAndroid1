package com.example.projetandroid1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestVehicleResponse {

    private int count;
    private List<Vehicle> results;

    public List<Vehicle> getResults() {
        return results;
    }
}
