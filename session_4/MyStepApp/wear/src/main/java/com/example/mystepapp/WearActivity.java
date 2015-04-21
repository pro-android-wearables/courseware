package com.example.mystepapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class WearActivity extends Activity {

  private static final String TAG = WearActivity.class.getSimpleName();

  private TextView mTextView;

  private CircledImageView detectorProgress;

  private TextView counterProgress;

  private SensorManager sensorManager;

  private Sensor detector;

  private Sensor counter;

  private static final int GOAL = 50;

  private float steps = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wear);
    final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
    stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
      @Override
      public void onLayoutInflated(WatchViewStub stub) {
        detectorProgress = (CircledImageView) stub.findViewById(R.id.detector);
        counterProgress = (TextView) stub.findViewById(R.id.counter);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

//        listSensors();

        detector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        sensorManager.registerListener(detectorListener, detector, 0);

        counter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        sensorManager.registerListener(counterListener, counter, 0);
      }
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    sensorManager.unregisterListener(detectorListener);
  }

  private void listSensors() {
    List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

    for(Sensor sensor : sensors){
      Log.d(TAG, sensor.getName());
    }
  }

  private SensorEventListener detectorListener = new SensorEventListener() {
    @Override
    public void onSensorChanged(SensorEvent event) {
      steps++;

      float progress = steps / GOAL;

      detectorProgress.setProgress(progress);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
  };

  private SensorEventListener counterListener = new SensorEventListener() {
    @Override
    public void onSensorChanged(SensorEvent event) {
      float steps = event.values[0];

      counterProgress.setText(Float.toString(steps));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
  };


}
