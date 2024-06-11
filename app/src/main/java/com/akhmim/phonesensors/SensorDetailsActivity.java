package com.akhmim.phonesensors;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SensorDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_details);

        // Récupérer les données du capteur passées depuis l'intent
        Intent intent = getIntent();
        if (intent != null) {
            String sensorName = intent.getStringExtra("sensor_name");
            int sensorType = intent.getIntExtra("sensor_type",-1);
            int sensorVersion = intent.getIntExtra("sensor_version",-1);
            String sensorVendor = intent.getStringExtra("sensor_vendor");



            // Afficher les détails du capteur dans les TextViews
            TextView nameTextView = findViewById(R.id.sensorNameTextView);
            TextView typeTextView1 = findViewById(R.id.sensorTypeTextView1);
            TextView typeTextView2 = findViewById(R.id.sensorTypeTextView2);
            TextView typeTextView3 = findViewById(R.id.sensorTypeTextView3);

            nameTextView.setText("Nom du capteur : " + sensorName);
            typeTextView1.setText("Type du capteur : " + sensorType);
            typeTextView2.setText("Version du capteur : " + sensorVersion);
            typeTextView3.setText("Vendor du capteur : " + sensorVendor);
        }
    }
}
