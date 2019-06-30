package com.example.projetandroid1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Target;
import java.util.List;

import javax.xml.transform.Source;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.projetandroid1.R.*;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        recyclerView = (RecyclerView) findViewById(id.my_recycler_view);
        getDataServer();
    }
    private void showList(List<Vehicle> results) {
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(results, getListener());
        recyclerView.setAdapter(mAdapter);
    }

    private MyAdapter.OnItemClickListener getListener() {
        return new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Vehicle item) {
                Intent intent;
                Gson gson = new Gson();
                intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("obj", gson.toJson(item));
                startActivity(intent);
            }
        };
    }

    public void getDataServer(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/")
                //convertisseur de json vers java
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        StarWarsApi restApi = retrofit.create(StarWarsApi.class);
        Call<RestVehicleResponse> call = restApi.getListVehicle();

        call.enqueue(new Callback<RestVehicleResponse>() {
            @Override
            public void onResponse(Call<RestVehicleResponse> call, Response<RestVehicleResponse> response) {
                RestVehicleResponse responseVehicle = response.body();
                showList(responseVehicle.getResults());
            }
            @Override
            public void onFailure(Call<RestVehicleResponse> call, Throwable t) {

            }
        });
    }

}