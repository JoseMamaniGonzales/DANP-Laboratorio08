package com.example.laboratorio08;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensorAcelerometer,sensorMagnetic;
    SensorEventListener sensorEvent,sensorEventMagnetic;
    TextView AceleX,AceleY,AceleZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorAcelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        AceleX = (TextView)findViewById(R.id.AceX);
        AceleY = (TextView)findViewById(R.id.AceY);
        AceleZ = (TextView)findViewById(R.id.AceZ);
        if(sensorAcelerometer ==null && sensorMagnetic ==null)
            finish();
        sensorEvent = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float X = sensorEvent.values[0];
                float Y = sensorEvent.values[1];
                float Z = sensorEvent.values[2];
                AceleX.setText("X: \t"+String.valueOf(X));
                AceleY.setText("Y: \t"+String.valueOf(Y));
                AceleZ.setText("Z: \t"+String.valueOf(Z));
               if(X>1 || X<-1 || Y>1 || Y<-1){
                    sonar();
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();
    }
    private void start(){
        sensorManager.registerListener(sensorEvent, sensorAcelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void sonar(){
        MediaPlayer mp=MediaPlayer.create(this,R.raw.sonido);
        mp.start();
    }

}