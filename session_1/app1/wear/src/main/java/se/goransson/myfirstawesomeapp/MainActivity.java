package se.goransson.myfirstawesomeapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

  private TextView mTextView;

  private Button mButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
    stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
      @Override
      public void onLayoutInflated(WatchViewStub stub) {
        mTextView = (TextView) stub.findViewById(R.id.text);

        mButton = (Button) stub.findViewById(R.id.button);
        mButton.setOnClickListener(buttonListener);
      }
    });
  }

  private View.OnClickListener buttonListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      // In here we can react
      mButton.setText("I'm clicked!!");
    }
  };
}
