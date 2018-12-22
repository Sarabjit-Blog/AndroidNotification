package blog.sarabjit.androidnotifications.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import blog.sarabjit.androidnotifications.BaseApplication;
import blog.sarabjit.androidnotifications.MainActivity;
import blog.sarabjit.androidnotifications.R;
import blog.sarabjit.androidnotifications.receiver.NotificationReceiver;

/**
 * Created by sarbagga on 05/10/18.
 */

public class Utils {

    private static final String Notification_ID = "notificationId";

    public static Notification createActionNotification(int notificationID, String notificationChannelID) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(), R.color.colorPrimaryDark));
        notificationBuilder.setContentTitle("Notification Action");
        notificationBuilder.setContentText("Click to Launch App");

        PendingIntent launchIntent = getLaunchIntent(notificationID);
        Intent viewIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        viewIntent.putExtra(Notification_ID, notificationID);
        viewIntent.putExtra("notificationChannelID", notificationChannelID);
        viewIntent.putExtra("link", "https://www.google.com");
        viewIntent.setAction("view");
        PendingIntent viewPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, viewIntent, 0);

        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(Notification_ID, notificationID);
        dismissIntent.putExtra("notificationChannelID", notificationChannelID);
        dismissIntent.setAction("dismiss");

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, 0);

        notificationBuilder.setContentIntent(launchIntent);
        notificationBuilder.addAction(android.R.drawable.ic_menu_view, "VIEW", viewPendingIntent);
        notificationBuilder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissPendingIntent);
        return notificationBuilder.build();

    }

    public static Notification createHeadsUpNotification(int notificationID, String notificationChannelID) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(), R.color.colorPrimaryDark));
        notificationBuilder.setContentTitle("Notification Action");
        notificationBuilder.setContentText("Click to Launch App");
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);


        PendingIntent launchIntent = getLaunchIntent(notificationID);
        Intent viewIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        viewIntent.putExtra(Notification_ID, notificationID);
        viewIntent.putExtra("notificationChannelID", notificationChannelID);
        viewIntent.putExtra("link", "https://www.google.com");
        viewIntent.setAction("view");
        PendingIntent viewPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, viewIntent, 0);

        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(Notification_ID, notificationID);
        dismissIntent.putExtra("notificationChannelID", notificationChannelID);
        dismissIntent.setAction("dismiss");

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, 0);

        notificationBuilder.setContentIntent(launchIntent);
        notificationBuilder.addAction(android.R.drawable.ic_menu_view, "VIEW", viewPendingIntent);
        notificationBuilder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissPendingIntent);
        return notificationBuilder.build();

    }

    public static Notification createLargeTextNotification(int notificationID, String notificationChannelID) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(), R.color.colorPrimaryDark));
        notificationBuilder.setContentTitle("Large Data Notification");
        notificationBuilder.setContentText("Slide to Enlarge");
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(
                BaseApplication.getInstance().getResources().getString(R.string.large_notification_text)));


        PendingIntent launchIntent = getLaunchIntent(notificationID);

        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(Notification_ID, notificationID);
        dismissIntent.putExtra("notificationChannelID", notificationChannelID);
        dismissIntent.setAction("dismiss");

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, 0);


        notificationBuilder.addAction(android.R.drawable.ic_menu_send, "OPEN", launchIntent);
        notificationBuilder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissPendingIntent);
        return notificationBuilder.build();

    }

    public static Notification createLargeImageNotification(int notificationID, String notificationChannelID) {
        Bitmap pic = BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(), R.drawable.background_image);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(), R.color.colorPrimaryDark));
        notificationBuilder.setContentTitle("Large Data Notification");
        notificationBuilder.setContentText("Slide to Enlarge");
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(pic));


        PendingIntent launchIntent = getLaunchIntent(notificationID);

        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(Notification_ID, notificationID);
        dismissIntent.putExtra("notificationChannelID", notificationChannelID);
        dismissIntent.setAction("dismiss");

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, 0);


        notificationBuilder.addAction(android.R.drawable.ic_menu_send, "OPEN", launchIntent);
        notificationBuilder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissPendingIntent);
        return notificationBuilder.build();

    }

    public static Notification createInboxTypeNotification(int notificationID, String notificationChannelID) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(), R.color.colorPrimaryDark));
        notificationBuilder.setContentTitle("Message Notification");
        notificationBuilder.setContentText("Inbox");

        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationBuilder.setStyle(new NotificationCompat.InboxStyle().addLine("Hi").addLine("How are you?").
                setBigContentTitle("2 New Messages").setSummaryText("Inbox"));


        PendingIntent launchIntent = getLaunchIntent(notificationID);

        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(Notification_ID, notificationID);
        dismissIntent.putExtra("notificationChannelID", notificationChannelID);
        dismissIntent.setAction("dismiss");

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, 0);


        notificationBuilder.addAction(android.R.drawable.ic_menu_send, "OPEN", launchIntent);
        notificationBuilder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissPendingIntent);
        return notificationBuilder.build();

    }

    public static Notification createMessageTypeNotification(int notificationID, String notificationChannelID) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(), R.color.colorPrimaryDark));
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationBuilder.setStyle(new NotificationCompat.MessagingStyle("Sarabjit").setConversationTitle("Friend Conversation").
                addMessage("How are you been doing", 0, "Lisha").
                addMessage("I am good, Where are you these days?", 0, null).
                addMessage("I am in Canada these days", 0, "Lisha").
                addMessage("Cool", 0, null));


        PendingIntent launchIntent = getLaunchIntent(notificationID);

        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(Notification_ID, notificationID);
        dismissIntent.putExtra("notificationChannelID", notificationChannelID);
        dismissIntent.setAction("dismiss");

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, 0);


        notificationBuilder.addAction(android.R.drawable.ic_menu_send, "OPEN", launchIntent);
        notificationBuilder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissPendingIntent);
        return notificationBuilder.build();

    }

    public static int getNotificationID(String algorithm) {
        try {
            Random addition = SecureRandom.getInstance(algorithm);
            return addition.nextInt(100) + 1;
        } catch (NoSuchAlgorithmException e) {
            Log.d("Utils.class", "" + e.getLocalizedMessage());
        }
        return -1;
    }

    private static PendingIntent getLaunchIntent(int notificationId) {
        Intent intent = new Intent(BaseApplication.getInstance(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Notification_ID, notificationId);
        return PendingIntent.getActivity(BaseApplication.getInstance(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static NotificationChannel createActionNotificationChannel(String notificationChannelID) {
        NotificationChannel notificationChannel = new NotificationChannel(notificationChannelID,
                "Notification", NotificationManager.IMPORTANCE_DEFAULT);
        // Configure the notification channel.
        notificationChannel.setDescription("Channel description");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
        notificationChannel.enableVibration(true);
        return notificationChannel;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static NotificationChannel createHeadsUpNotificationChannel(String notificationChannelID) {
        NotificationChannel notificationChannel = new NotificationChannel(notificationChannelID,
                "Notification", NotificationManager.IMPORTANCE_HIGH);
        // Configure the notification channel.
        notificationChannel.setDescription("Channel description");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
        notificationChannel.enableVibration(true);
        return notificationChannel;
    }
}
