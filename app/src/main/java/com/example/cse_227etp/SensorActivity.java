package com.example.cse_227etp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    TextView tvSensor, tvSensorList;
    SensorManager sensorManager;
    Sensor lightSensor, tempSensor, proxiSensor, accSensor, magSensor;

    float [] accarr = new float[3];
    float [] magarr = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        tvSensor = findViewById(R.id.sensor_tv_sensor);
        tvSensorList = findViewById(R.id.sensor_tv_sensor_list);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        findViewById(R.id.sensor_btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
                StringBuilder stringBuilder = new StringBuilder();
                for(Sensor s : sensorList){
                    String s1 = s.getName()+" vendor : "+ s.getVendor()+" version : "+s.getVersion();
                    stringBuilder.append(s1+"\n");
                }
                tvSensorList.setText(stringBuilder);
            }
        });
        if(sensorManager != null){
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            proxiSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            magSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }else {
            Toast.makeText(this,"Sensor Manager is Null!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(lightSensor != null){
            sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(tempSensor != null){
            sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(proxiSensor != null){
            sensorManager.registerListener(this,proxiSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(accSensor != null){
            sensorManager.registerListener(this,accSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(magSensor != null){
            sensorManager.registerListener(this,magSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                float lux = sensorEvent.values[0];
                Toast.makeText(getApplicationContext(), "brightness is "+lux, Toast.LENGTH_SHORT).show();
                break;
            case Sensor.TYPE_PROXIMITY:
                float[] value  = sensorEvent.values;
                Toast.makeText(getApplicationContext(), "Distance is "+ value[0], Toast.LENGTH_SHORT).show();
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                float lux1 = sensorEvent.values[0];
                Toast.makeText(getApplicationContext(), "temperature is "+lux1, Toast.LENGTH_SHORT).show();
                break;
            case Sensor.TYPE_ACCELEROMETER:
                accarr = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                magarr = sensorEvent.values.clone();
                break;
        }
        float[] rotationMatrix = new float[9];

        boolean rotationOK = SensorManager.getRotationMatrix(rotationMatrix, null,accarr, magarr);

        if(rotationOK) {
            float [] orientation = new float[3];
            SensorManager.getOrientation(rotationMatrix, orientation);
            float azimut = orientation[0]; //degrees of rotation about the -z axis
            float pitch = orientation[1]; //degrees of rotation about the x axis
            float   roll = orientation[2]; //degrees of rotation about the y axis
            tvSensor.setText("azimut is : " + azimut +"\n pitch is : "+ pitch +"\n roll is : "+roll);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
