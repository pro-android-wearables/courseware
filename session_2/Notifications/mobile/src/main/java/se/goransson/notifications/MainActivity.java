package se.goransson.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

  NotificationManager notificationManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    showNotification(1, "basic", getBasicNotification("myStack"));
//    showNotification(2, "bigpic", getBigPictureNotification("myStack"));
//    showNotification(3, "pages", getPagesNotification("myStack2"));
//    showNotification(4, "action", getActionableNotification(null));
//    showNotification(5, "actions", getMultiActionableNotification("myStack2"));
  }

  /**
   * Show a notification.
   *
   * @param id           The notification id
   * @param tag          The notification tag
   * @param notification The notification
   */
  private void showNotification(int id, String tag, Notification notification) {
    notificationManager.notify(tag, id, notification);
  }

  /**
   * Get the basic notification
   *
   * @param stack The stack, null if no stack
   * @return
   */
  private Notification getBasicNotification(String stack) {
    String title = "My notification";
    String text = "This is my first notification";

    return new Notification.Builder(this)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(title)
        .setContentText(text)
        .setGroup(stack)
        .build();
  }

  /**
   * Get the big picture style notification
   *
   * @param stack The stack id, null if no stack
   * @return The notification
   */
  private Notification getBigPictureNotification(String stack) {
    String title = "My photo";
    String summary = "This is my scenic photo";
    Bitmap bigpic = BitmapFactory.decodeResource(getResources(), R.drawable.bigpic);
    String text = "This is the notification text";

    Notification.Style style = new Notification.BigPictureStyle()
        .setBigContentTitle(title)
        .setSummaryText(summary)
        .bigPicture(bigpic);

    return new Notification.Builder(this)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(title)
        .setContentText(text)
        .setStyle(style)
        .setGroup(stack)
        .build();
  }

  /**
   * Get the big picture style notification
   *
   * @param stack The stack id, null if no stack
   * @return The Notification
   */
  private Notification getPagesNotification(String stack) {
    String title = "My notification";
    String text = "This has more pages";

    List<Notification> pages = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      Notification notification = new Notification.Builder(this)
          .setContentTitle("Page " + i)
          .setContentText("Text for page " + i)
          .build();
      pages.add(notification);
    }

    Notification.WearableExtender wearableExtender = new Notification.WearableExtender()
        .addPages(pages);

    return new Notification.Builder(this)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(title)
        .setContentText(text)
        .extend(wearableExtender)
        .setGroup(stack)
        .build();
  }

  /**
   * Get the standard actionable notification
   *
   * @param stack The stack id, null if no stack
   * @return The Notification
   */
  private Notification getActionableNotification(String stack) {
    String title = "My notification";
    String text = "This notification has an action!";

    Intent action = new Intent(this, MainActivity.class);

    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, action,
        PendingIntent.FLAG_UPDATE_CURRENT);

    return new Notification.Builder(this)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(title)
        .setContentText(text)
        .setContentIntent(pendingIntent)
        .setGroup(stack)
        .build();
  }

  /**
   * Get the notification with wearable-only actions
   *
   * @param stack The stack id, null if no stack
   * @return The notification
   */
  private Notification getMultiActionableNotification(String stack) {
    String title = "My notification";
    String text = "This notification has wear only actions!";

    String action1Label = "open app";
    Intent intent1 = new Intent(this, MainActivity.class);
    PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, intent1,
        PendingIntent.FLAG_UPDATE_CURRENT);
    Notification.Action action1 = new Notification.Action.Builder(R.mipmap.ic_launcher,
        action1Label, pendingIntent1).build();

    String action2Label = "open dialer";
    Intent intent2 = new Intent(Intent.ACTION_DIAL);
    PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2,
        PendingIntent.FLAG_UPDATE_CURRENT);
    Notification.Action action2 = new Notification.Action.Builder(R.mipmap.ic_launcher,
        action2Label, pendingIntent2).build();

    Notification.WearableExtender wearableExtender = new Notification.WearableExtender()
        .addAction(action1)
        .addAction(action2);

    return new Notification.Builder(this)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(title)
        .setContentText(text)
        .extend(wearableExtender)
        .setGroup(stack)
        .build();
  }
}
