package com.akhmim.phonesensors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import com.akhmim.phonesensors.R;
import com.akhmim.phonesensors.SensorDetailsActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private ListView sensorListView;
    private ArrayAdapter<String> adapter;
    private List<Sensor> sensorList;
    TextView tv1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorListView = findViewById(R.id.sensorListView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);

        ArrayList<String> sensorNames = new ArrayList<>();
        for (Sensor sensor : sensorList) {
            sensorNames.add(sensor.getName());
        }
        tv1 = findViewById(R.id.tv1);

        for (int i = 1; i < list.size(); i++) {
            tv1.append("\n" + "\n" + "Numero of sensor:" + i + "/" + list.size() + "\n" + list.get(i).getName() + "\n" + list.get(i).getVendor() + "\n" + list.get(i).getVersion());
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sensorNames);
        sensorListView.setAdapter(adapter);

        sensorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sensor selectedSensor = sensorList.get(position);
                Intent intent = new Intent(SensorActivity.this, SensorDetailsActivity.class);
                intent.putExtra("sensor_name", selectedSensor.getName());
                intent.putExtra("sensor_type", selectedSensor.getType());
                intent.putExtra("sensor_version", selectedSensor.getVersion());
                intent.putExtra("sensor_vendor", selectedSensor.getVendor());
                // Ajoutez d'autres détails du capteur à passer si nécessaire
                startActivity(intent);
            }
        });


    }

    public static void saveTextViewAsTextFile(Context context, TextView textView, String fileName) {

        String textToSave = textView.getText().toString();

//        Context context;
        if (!isExternalStorageWritable()) {
            Toast.makeText(context, "External storage not available", Toast.LENGTH_SHORT).show();
            return;
        }

        File textFile = new File(context.getExternalFilesDir(null), fileName);

        try {
            FileOutputStream fos = new FileOutputStream(textFile);
            fos.write(textToSave.getBytes());
            fos.close();
            Toast.makeText(context, "Text saved to " + textFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to save text", Toast.LENGTH_SHORT).show();
        }
    }

    /* Checks if external storage is available for read and write */
    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

}
