package com.example.session3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Random;

public class MobileActivity extends Activity {

  /**
   * This is used for tagging log messages
   */
  private static final String TAG = MobileActivity.class.getSimpleName();

  /**
   * Used to request an image form the camera
   */
  static final int REQUEST_IMAGE_CAPTURE = 1;

  /**
   * The input element
   */
  private EditText messageInput;

  /**
   * This is our entry point to getting information about connected nodes, sending messages and
   * sending data between devices.
   * <p/>
   * This client can also be used for Google+, Drive, and others.
   */
  private GoogleApiClient googleApiClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mobile);

    messageInput = (EditText) findViewById(R.id.message_input);

    // Compose the Google API client
    createGoogleApi();
  }

  @Override
  protected void onStart() {
    super.onStart();

    // Connect to the Google API client we built in onCreate
    googleApiClient.connect();
  }

  @Override
  protected void onStop() {
    super.onStop();

    // When the activity dies the connection should stop - just call disconnect
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
      Log.d(TAG, "Connected to " + bundle);
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
      Log.d(TAG, "Connection failed because of " + connectionResult);
    }
  };

  /**
   * This method gets executed when our button is pressed. The link is set within the layout.
   *
   * @param v
   */
  public void sendMessageToWear(View v) {
    // Get the message as a string from the input
    String text = messageInput.getText().toString();

    // Verify that there is something written
    if (text.length() > 0) {
      // Get the nodes and send the result to the callback method "nodesResult".
      Wearable.NodeApi.getConnectedNodes(googleApiClient).setResultCallback(sendMessageResult);
    } else {
      Log.e(TAG, "Dude! You gotta write something!");
    }
  }

  /**
   * This is our callback for when we want to get a list of all connected nodes. Something we'll do
   * whenever we send a new message to Wear/s.
   * <p/>
   * In this method we're doing a "fire and forget" time of message - we don't even listen for the
   * result of the sent message.
   */
  private ResultCallback sendMessageResult = new ResultCallback<NodeApi.GetConnectedNodesResult>() {

    @Override
    public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
      // Again, get the text - but this is the last time we get the message!
      String text = messageInput.getText().toString();

      // Get all connected nodes (probably just the one...)
      List<Node> nodes = getConnectedNodesResult.getNodes();

      Log.d(TAG, "Found " + nodes.size() + " nodes");

      // Send the same message to all nodes
      for (Node node : nodes) {
        // Notice that we're just sending the message, we don't listen for the result
        Wearable.MessageApi.sendMessage(googleApiClient, node.getId(), "/message", text.getBytes());
      }

      // When we've sent the message let's just clear the input
      messageInput.setText("");
    }
  };

  /**
   * Send a new photo (thumbnail) to Wear
   *
   * @param v
   */
  public void sendPhotoToWear(View v) {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      Bundle extras = data.getExtras();
      Bitmap thumbnail = (Bitmap) extras.get("data");

      sendBitmapToWear(thumbnail);
    }
  }

  /**
   * This is the actual method for sending a bitmap to Wear
   *
   * @param thumbnail
   */
  private void sendBitmapToWear(Bitmap thumbnail) {
    // Get the byte[] from the thumbnail
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    thumbnail.compress(Bitmap.CompressFormat.PNG, 100, stream);
    byte[] data = stream.toByteArray();

    // Create a request
    PutDataMapRequest request = PutDataMapRequest.create("/image");
    DataMap map = request.getDataMap();

    // Create the asset from the raw thumbnail
    Asset thumbnailAsset = Asset.createFromBytes(data);

    // Attach the asset to the request
    map.putAsset("thumbnail", thumbnailAsset);

    // Finally, send the request
    Wearable.DataApi.putDataItem(googleApiClient, request.asPutDataRequest());
  }
}
