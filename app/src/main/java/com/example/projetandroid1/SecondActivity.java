package com.example.projetandroid1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("obj");
        Vehicle vehicle = gson.fromJson(strObj, Vehicle.class);
        fillSecondActivity(vehicle);
    }
    private void fillSecondActivity(Vehicle vehicle){
        TextView txtTitle = (TextView) findViewById(R.id.titleDetails);
        TextView txtLength = (TextView) findViewById(R.id.titleLenght2);
        TextView txtCargo = (TextView) findViewById(R.id.titleCapacity);
        txtTitle.setText(vehicle.getManufacturer());
        txtCargo.setText(vehicle.getCargo_capacity());
        txtLength.setText(vehicle.getLength());
    }
}
