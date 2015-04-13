package com.example.session3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;

import java.io.InputStream;

public class WearActivity extends Activity {

  /**
   * Just used when we print to logcat
   */
  private static final String TAG = WearActivity.class.getSimpleName();

  /**
   * We'll print messages from Mobile here.
   */
  private TextView message;

  /**
   * The thumbnail will end up here
   */
  private ImageView image;

  /**
   * Again, the google api client, we need it to start listening for messages.
   */
  private GoogleApiClient googleApiClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wear);
    final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
    stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
      @Override
      public void onLayoutInflated(WatchViewStub stub) {
        message = (TextView) stub.findViewById(R.id.text);

        image = (ImageView) stub.findViewById(R.id.image);
      }
    });

    // Compose the Google API client
    createGoogleApi();

    // Connect
    googleApiClient.connect();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    // ...and of course don't forget to disconnect.
    googleApiClient.disconnect();
  }

  /**
   * This method just connects to Google API:s
   */
  private void createGoogleApi() {
    if (googleApiClient != null && googleApiClient.isConnected()) {
      Log.d(TAG, "I'm already connected, no need to recreate the client");
    } else {
      googleApiClient = new GoogleApiClient.Builder(this)
          // Add the wearable API, because we're working with Wear
          .addApi(Wearable.API)
              // Add connection callbacks - these let us listen to when things connect and disconnect
          .addConnectionCallbacks(connectionCallbacks)
              // Also listen for failed attempts
          .addOnConnectionFailedListener(connectionFailedListener)
              // Build the client stack
          .build();
    }
  }

  /**
   * This is used for listening to connection changes
   */
  private GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
    @Override
    public void onConnected(Bundle bundle) {
      // Something was connected.
      Log.d(TAG, "Connected");

      // When we connect we'll set up listeners for both Message...
      Wearable.MessageApi.addListener(googleApiClient, messageListener);

      // ...and Data API:s
      Wearable.DataApi.addListener(googleApiClient, dataListener);
    }

    @Override
    public void onConnectionSuspended(int reason) {
      // Something was disconnected
      Log.d(TAG, "Disconnected for reason " + reason);
    }
  };

  private GoogleApiClient.OnConnectionFailedListener connectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
      Log.d(TAG, "Connection failed because of " + connectionResult.getErrorCode());
    }
  };

  /**
   * This is where we'll get the "fire and forget" messages that the mobile is sending us.
   */
  private MessageApi.MessageListener messageListener = new MessageApi.MessageListener() {
    @Override
    public void onMessageReceived(final MessageEvent messageEvent) {
      // Make sure we're on the right "path"
      if (messageEvent.getPath().equals("/message")) {
        // There is no documentation suggesting why this listener is run off the UI thread...
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            // The data is send in byte[] (remember?) so we'll need to convert it back to String
            String text = new String(messageEvent.getData());

            // Set the output to our recieved message
            message.setText(text);
          }
        });
      } else {
        Log.e(TAG, "I didn't recognize the path, so I ignored the message!");
      }
    }
  };

  /**
   * Whenever we get something through the Data API we'll receive it in this listener.
   */
  private DataApi.DataListener dataListener = new DataApi.DataListener() {
    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
      for (DataEvent event : dataEvents) {
        if (event.getType() == DataEvent.TYPE_CHANGED && event.getDataItem().getUri().getPath().equals("/image")) {
          // Get the map from the data object we sent
          DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());

          // extract the asset
          Asset thumbnailAsset = dataMapItem.getDataMap().getAsset("thumbnail");

          // Convert the asset back to a bitmap
          convertAssetToBitmap(thumbnailAsset);
        }
      }
    }
  };

  private void convertAssetToBitmap(Asset asset) {
    if (asset == null) {
      throw new IllegalArgumentException("The asset was empty, that's not right!");
    }

    Wearable.DataApi.getFdForAsset(googleApiClient, asset).setResultCallback(convertResultCallback);
  }

  /**
   * We'll get the converted image here
   */
  private ResultCallback convertResultCallback = new ResultCallback<DataApi.GetFdForAssetResult>() {

    @Override
    public void onResult(DataApi.GetFdForAssetResult getFdForAssetResult) {
      InputStream inputStream = getFdForAssetResult.getInputStream();

      Bitmap thumbnail = BitmapFactory.decodeStream(inputStream);

      image.setImageBitmap(thumbnail);
    }
  };

}
