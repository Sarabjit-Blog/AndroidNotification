package blog.sarabjit.androidnotifications.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra("notificationId", 0);
        String notificationChannelId = intent.getStringExtra("notificationchannelID");
        String action = intent.getAction();
        if (action != null && action.equalsIgnoreCase("view")) {
            String downloadLink = intent.getStringExtra("link");
            Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadLink));
            context.startActivity(viewIntent);
        }
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.cancel(notificationId);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager.deleteNotificationChannel(notificationChannelId);
            }
        }
    }
}