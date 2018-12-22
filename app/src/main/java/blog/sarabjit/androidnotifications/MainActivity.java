package blog.sarabjit.androidnotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import blog.sarabjit.androidnotifications.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private TextView tvActionNotification;
    private TextView tvHeadsUpNotification;
    private TextView tvLargeTextNotification;
    private TextView tvInboxStyleNotification;
    private TextView tvMessageStyleNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initializeListeners();
    }

    private void initializeViews() {
        tvActionNotification = findViewById(R.id.notificationAction);
        tvHeadsUpNotification = findViewById(R.id.headsUpNotification);
        tvLargeTextNotification = findViewById(R.id.largeTextNotification);
        tvInboxStyleNotification = findViewById(R.id.inboxNotification);
        tvMessageStyleNotification = findViewById(R.id.messageNotification);
    }

    private void initializeListeners() {
        tvHeadsUpNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createHeadsUpNotification();
            }
        });

        tvActionNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createActionNotification();
            }
        });

        tvLargeTextNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createLargeTextNotification();
            }
        });

        tvMessageStyleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMessageStyleNotification();
            }
        });

        tvInboxStyleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createInboxStyleNotification();
            }
        });
    }

    private void createLargeTextNotification() {
        int notificationID = Utils.getNotificationID("SHA1PRNG");
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_largeText";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createHeadsUpNotificationChannel(strNotificationChannel);
            }
            Notification notification = Utils.createLargeTextNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                    getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null && notificationChannel != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d("error", "Something went wrong");
        }
    }

    private void createInboxStyleNotification() {
        int notificationID = Utils.getNotificationID("SHA1PRNG");
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_inbox_style";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createHeadsUpNotificationChannel(strNotificationChannel);
            }
            Notification notification = Utils.createInboxTypeNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                    getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null && notificationChannel != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d("error", "Something went wrong");
        }

    }

    private void createMessageStyleNotification() {
        int notificationID = Utils.getNotificationID("SHA1PRNG");
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_message_style";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createHeadsUpNotificationChannel(strNotificationChannel);
            }
            Notification notification = Utils.createMessageTypeNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                    getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null && notificationChannel != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d("error", "Something went wrong");
        }

    }

    private void createHeadsUpNotification() {
        int notificationID = Utils.getNotificationID("SHA1PRNG");
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_headsUp";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createHeadsUpNotificationChannel(strNotificationChannel);
            }
            Notification notification = Utils.createHeadsUpNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                    getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null && notificationChannel != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d("error", "Something went wrong");
        }

    }

    private void createActionNotification() {
        int notificationID = Utils.getNotificationID("SHA1PRNG");
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_action";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createActionNotificationChannel(strNotificationChannel);
            }
            Notification notification = Utils.createActionNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                    getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null && notificationChannel != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d("error", "Something went wrong");
        }
    }
}
