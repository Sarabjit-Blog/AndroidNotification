package blog.sarabjit.androidnotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import blog.sarabjit.androidnotifications.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tvActionNotification;
    private TextView tvOverTheTopNotification;
    private TextView tvLargeTextNotification;
    private TextView tvInboxStyleNotification;
    private TextView tvMessageStyleNotification;
    private TextView tvImageStyleNotification;
    private TextView tvReplyNotification;
    private TextView tvMediaNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initializeListeners();
    }

    /**
     * Initialize Views
     */
    private void initializeViews() {
        tvActionNotification = findViewById(R.id.notificationAction);
        tvOverTheTopNotification = findViewById(R.id.headsUpNotification);
        tvLargeTextNotification = findViewById(R.id.largeTextNotification);
        tvInboxStyleNotification = findViewById(R.id.inboxNotification);
        tvMessageStyleNotification = findViewById(R.id.messageNotification);
        tvImageStyleNotification = findViewById(R.id.imageNotification);
        tvReplyNotification = findViewById(R.id.replyNotification);
        tvMediaNotification = findViewById(R.id.mediaNotification);
    }

    /**
     * Initialize Listeners
     */
    private void initializeListeners() {
        tvOverTheTopNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOverTheTopNotification();
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

        tvImageStyleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createImageNotification();
            }
        });

        tvReplyNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createReplyNotification();
            }
        });

        tvMediaNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startServiceWithMediaNotification();
            }
        });

    }

    /**
     * Create Image Notification and display to User
     */
    private void createImageNotification() {
        int notificationID = Utils.getNotificationId();
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                getSystemService(NOTIFICATION_SERVICE);
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;

            String strNotificationChannel = "notification_channel_image_style";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createOverTheTopNotificationChannel(notificationManager, strNotificationChannel);
            }
            Notification notification = Utils.imageNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            if (notificationManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationChannel != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d(TAG, getString(R.string.image_style_notification_error));
        }
    }

    /**
     * Create Reply Notification
     */
    private void createReplyNotification() {
        int notificationID = Utils.getNotificationId();
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                getSystemService(NOTIFICATION_SERVICE);
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_reply_style";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createOverTheTopNotificationChannel(notificationManager, strNotificationChannel);
            }
            Notification notification = Utils.createReplyNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            if (notificationManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationChannel != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d(TAG, getString(R.string.large_text_notification_error));
        }
    }

    /**
     * Create Large Text Notification
     */
    private void createLargeTextNotification() {
        int notificationID = Utils.getNotificationId();
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                getSystemService(NOTIFICATION_SERVICE);
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_largeText_style";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createOverTheTopNotificationChannel(notificationManager, strNotificationChannel);
            }
            Notification notification = Utils.createLargeTextNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            if (notificationManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationChannel != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d(TAG, getString(R.string.large_text_notification_error));
        }
    }

    /**
     * Create Inbox Style Notification
     */
    private void createInboxStyleNotification() {
        int notificationID = Utils.getNotificationId();
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                getSystemService(NOTIFICATION_SERVICE);

        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_inbox_style";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createOverTheTopNotificationChannel(notificationManager, strNotificationChannel);
            }
            Notification notification = Utils.createInboxTypeNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            if (notificationManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationChannel != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d(TAG, getString(R.string.inbox_style_notification_error));
        }

    }

    /**
     * Create Message Style Notification
     */
    private void createMessageStyleNotification() {
        int notificationID = Utils.getNotificationId();
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                getSystemService(NOTIFICATION_SERVICE);
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_message_style";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createOverTheTopNotificationChannel(notificationManager, strNotificationChannel);
            }
            Notification notification = Utils.createMessageTypeNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            if (notificationManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationChannel != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d(TAG, getString(R.string.message_style_notification_error));
        }
    }

    /**
     * Create Over the Top Notification
     */
    private void createOverTheTopNotification() {
        int notificationID = Utils.getNotificationId();
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                getSystemService(NOTIFICATION_SERVICE);
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_over_the_top";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createOverTheTopNotificationChannel(notificationManager, strNotificationChannel);
            }
            Notification notification = Utils.createOverTheTopNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            if (notificationManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationChannel != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d(TAG, getString(R.string.heads_up_notification_error));
        }
    }

    /**
     * Display Action Notification
     */
    private void createActionNotification() {
        int notificationID = Utils.getNotificationId();
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                getSystemService(NOTIFICATION_SERVICE);
        if (notificationID > -1) {
            NotificationChannel notificationChannel = null;
            String strNotificationChannel = "notification_channel_action";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = Utils.createActionNotificationChannel(notificationManager, strNotificationChannel);
            }
            Notification notification = Utils.createActionNotification(notificationID, strNotificationChannel);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            if (notificationManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationChannel != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(notificationID, notification);
            }
        } else {
            Log.d(TAG, getString(R.string.action_notification_error));
        }
    }

    /**
     * Display Media Style Notification
     */
    private void startServiceWithMediaNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String strNotificationChannel = "notificationMediaChannel";
            Intent intent = new Intent(getApplicationContext(), MusicPlayerService.class);
            intent.setAction(String.valueOf(Utils.PLAY));
            Bundle bundle = new Bundle();
            bundle.putInt(Utils.NOTIFICATION_ID, Utils.getNotificationId());
            bundle.putString(Utils.NOTIFICATION_CHANNEL, strNotificationChannel);
            intent.putExtras(bundle);
            startService(intent);
        } else {
            Toast.makeText(getApplicationContext(), R.string.media_notification_error, Toast.LENGTH_LONG).show();
        }
    }
}
