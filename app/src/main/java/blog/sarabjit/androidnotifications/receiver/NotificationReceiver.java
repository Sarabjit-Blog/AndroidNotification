package blog.sarabjit.androidnotifications.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import blog.sarabjit.androidnotifications.MainActivity;
import blog.sarabjit.androidnotifications.utils.Utils;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra(Utils.NOTIFICATION_ID, 0);
        String notificationChannelId = intent.getStringExtra(Utils.NOTIFICATION_CHANNEL);
        String action = intent.getAction();
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.cancel(notificationId);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationChannelId != null) {
                manager.deleteNotificationChannel(notificationChannelId);
            }
        }
        if (action != null && action.equalsIgnoreCase(Utils.VIEW)) {
            String downloadLink = intent.getStringExtra(Utils.LINK);
            if (downloadLink != null) {
                Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadLink));
                viewIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(viewIntent);
            } else {
                Intent openAppIntent = new Intent(context, MainActivity.class);
                openAppIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(openAppIntent);
            }
        } else if (action != null && action.equalsIgnoreCase(Utils.REPLY_ACTION)) {
            CharSequence message = Utils.getReplyMessage(intent);
            int messageId = intent.getIntExtra(Utils.MESSAGE_ID, 0);
            Toast.makeText(context, "Message ID: " + messageId + "\nMessage: " + message,
                    Toast.LENGTH_SHORT).show();
        }
    }


}