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
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.Person;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import blog.sarabjit.androidnotifications.BaseApplication;
import blog.sarabjit.androidnotifications.MainActivity;
import blog.sarabjit.androidnotifications.MusicPlayerService;
import blog.sarabjit.androidnotifications.R;
import blog.sarabjit.androidnotifications.ReplyActivity;
import blog.sarabjit.androidnotifications.receiver.NotificationReceiver;

/**
 * Created by sarbjit Bagga on 05/10/18.
 */

public class Utils {

    public static final String REPLY = "Reply";
    public static final String REPLY_ACTION = "REPLY_ACTION";
    public static final String MESSAGE_ID = "messageId";
    public static final String NOTIFICATION_ID = "notificationId";
    public static final String NOTIFICATION_CHANNEL = "notificationChannel";
    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int REWIND = 3;
    public static final int FAST_FORWARD = 4;
    public static final int NEXT = 5;
    public static final int PREVIOUS = 6;
    public static final int STOP = 7;
    public static final String LINK = "link";
    public static final String VIEW = "VIEW";
    private static final String ALGORITHM = "SHA1PRNG";
    private static final String DISMISS = "DISMISS";
    private static final String CHANNEL_NAME = "NOTIFICATION";
    private static final String CLICK_TO_OPEN = "Click to Open App";
    private static final String NOTIFICATION_CHANNEL_ID = "notificationChannelID";
    private static final String CLICK_TO_OPEN_URL = "Click View to Open Url";
    private static final String NOTIFICATION_CHANNEL_DESCRIPTION = "NOTIFICATION_CHANNEL_DESCRIPTION";
    private static final String URL = "https://www.google.com";
    private static final String TAG = "Utils";
    private static final String GROUP_KEY_BUNDLED = "INBOX_GROUP";

    private Utils() {
    }

    /**
     * Create Action Notification
     *
     * @param notificationID        Notification ID
     * @param notificationChannelID Notification Channel ID
     * @return
     */
    public static Notification createActionNotification(int notificationID,
                                                        String notificationChannelID) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(),
                R.color.colorPrimaryDark));
        notificationBuilder.setContentTitle("Notification Action");
        notificationBuilder.setContentText(CLICK_TO_OPEN);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        notificationBuilder.setVibrate(new long[0]);

        PendingIntent launchIntent = getLaunchIntent(notificationID);


        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(NOTIFICATION_ID, notificationID);
        dismissIntent.putExtra(NOTIFICATION_CHANNEL_ID, notificationChannelID);
        dismissIntent.setAction(DISMISS);

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        notificationBuilder.setContentIntent(launchIntent);
        notificationBuilder.addAction(android.R.drawable.ic_delete, DISMISS, dismissPendingIntent);
        return notificationBuilder.build();

    }

    /**
     * Create Over-the-top Notification
     *
     * @param notificationID        Notification ID
     * @param notificationChannelID Notification Channel ID
     * @return
     */
    public static Notification createOverTheTopNotification(int notificationID,
                                                            String notificationChannelID) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(),
                R.color.colorPrimaryDark));
        notificationBuilder.setContentTitle("Notification Action");
        notificationBuilder.setContentText(CLICK_TO_OPEN_URL);
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        notificationBuilder.setVibrate(new long[0]);

        PendingIntent launchIntent = getLaunchIntent(notificationID);

        Intent viewIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        viewIntent.putExtra(NOTIFICATION_ID, notificationID);
        viewIntent.putExtra(NOTIFICATION_CHANNEL_ID, notificationChannelID);
        viewIntent.putExtra(LINK, URL);
        viewIntent.setAction(VIEW);

        PendingIntent viewPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, viewIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(NOTIFICATION_ID, notificationID);
        dismissIntent.putExtra(NOTIFICATION_CHANNEL_ID, notificationChannelID);
        dismissIntent.setAction(DISMISS);

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        notificationBuilder.setContentIntent(launchIntent);
        notificationBuilder.addAction(android.R.drawable.ic_menu_view, VIEW, viewPendingIntent);
        notificationBuilder.addAction(android.R.drawable.ic_delete, DISMISS, dismissPendingIntent);
        return notificationBuilder.build();

    }

    /**
     * Create Large Text Notification
     *
     * @param notificationID        Notification ID
     * @param notificationChannelID Notification Channel ID
     * @return
     */
    public static Notification createLargeTextNotification(int notificationID,
                                                           String notificationChannelID) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(),
                R.color.colorPrimaryDark));
        notificationBuilder.setContentTitle("Large Data Notification");
        notificationBuilder.setContentText(CLICK_TO_OPEN);
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        notificationBuilder.setVibrate(new long[0]);


        notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(
                BaseApplication.getInstance().getResources().getString(R.string.large_notification_text)));
        notificationBuilder.setContentIntent(getLaunchIntent(notificationID));

        Intent viewIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        viewIntent.putExtra(NOTIFICATION_ID, notificationID);
        viewIntent.putExtra(NOTIFICATION_CHANNEL_ID, notificationChannelID);
        viewIntent.setAction(VIEW);
        PendingIntent viewPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, viewIntent, PendingIntent.FLAG_CANCEL_CURRENT);


        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(NOTIFICATION_ID, notificationID);
        dismissIntent.putExtra(NOTIFICATION_CHANNEL_ID, notificationChannelID);
        dismissIntent.setAction(DISMISS);

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, PendingIntent.FLAG_CANCEL_CURRENT);


        notificationBuilder.addAction(android.R.drawable.ic_menu_send, VIEW, viewPendingIntent);
        notificationBuilder.addAction(android.R.drawable.ic_delete, DISMISS, dismissPendingIntent);
        return notificationBuilder.build();
    }

    /**
     * Creating Large Image Notification
     *
     * @param notificationID        Notification ID
     * @param notificationChannelID Notification Channel ID
     * @return
     */
    public static Notification imageNotification(int notificationID,
                                                 String notificationChannelID) {
        Bitmap pic = BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(),
                R.drawable.background_image);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(),
                R.color.colorPrimaryDark));
        notificationBuilder.setContentTitle("Image Notification");
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setVibrate(new long[0]);
        notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(pic).
                setSummaryText(BaseApplication.getInstance().getString(R.string.image_preview_text)).
                setBigContentTitle(BaseApplication.getInstance().getString(R.string.image_content_title)));
        notificationBuilder.setContentIntent(getLaunchIntent(notificationID));

        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(NOTIFICATION_ID, notificationID);
        dismissIntent.putExtra(NOTIFICATION_CHANNEL_ID, notificationChannelID);
        dismissIntent.setAction(DISMISS);

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        notificationBuilder.addAction(android.R.drawable.ic_delete, DISMISS, dismissPendingIntent);
        return notificationBuilder.build();
    }

    /**
     * Creating Inbox Style Notification
     *
     * @param notificationID        notification ID
     * @param notificationChannelID Notification Channel ID
     * @return
     */
    public static Notification createInboxTypeNotification(int notificationID,
                                                           String notificationChannelID) {
        String sender = "Lisha";
        String subject = "Last Conversation";
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(),
                R.color.colorPrimaryDark));
        notificationBuilder.setContentTitle("5 New messages");
        notificationBuilder.setContentText(subject);
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        notificationBuilder.setVibrate(new long[0]);
        notificationBuilder.setStyle(new NotificationCompat.InboxStyle()
                .addLine("Hi")
                .addLine("How are you?")
                .addLine("Where are you these days ?")
                .setBigContentTitle("5 New Messages from " + sender)
                .setSummaryText("+2 more"));
        notificationBuilder.setContentIntent(getLaunchIntent(notificationID));

        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(NOTIFICATION_ID, notificationID);
        dismissIntent.putExtra(NOTIFICATION_CHANNEL_ID, notificationChannelID);
        dismissIntent.setAction(DISMISS);
        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        notificationBuilder.addAction(android.R.drawable.ic_delete, DISMISS, dismissPendingIntent);
        return notificationBuilder.build();

    }

    /**
     * Creating Message Type Notification
     *
     * @param notificationID        Notification ID
     * @param notificationChannelID Notification Channel ID
     * @return
     */
    public static Notification createMessageTypeNotification(int notificationID,
                                                             String notificationChannelID) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(),
                R.color.colorPrimaryDark));
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setContentIntent(getLaunchIntent(notificationID));

        NotificationCompat.MessagingStyle messagingStyle = getMessagingStyle();
        notificationBuilder.setStyle(messagingStyle);

        Intent dismissIntent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
        dismissIntent.putExtra(NOTIFICATION_ID, notificationID);
        dismissIntent.putExtra(NOTIFICATION_CHANNEL_ID, notificationChannelID);
        dismissIntent.setAction(DISMISS);

        PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(BaseApplication.getInstance(),
                0, dismissIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        notificationBuilder.addAction(android.R.drawable.ic_delete, DISMISS, dismissPendingIntent);
        return notificationBuilder.build();
    }

    public static Notification createReplyNotification(int notificationID, String notificationChannelID) {
        int messageID = 101;
        String replyLabel = BaseApplication.getInstance().getString(R.string.notification_reply);

        RemoteInput remoteInput = new RemoteInput.Builder(REPLY)
                .setLabel(replyLabel)
                .build();

        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                android.R.drawable.ic_menu_send, replyLabel, getReplyPendingIntent(notificationID,
                messageID))
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true)
                .build();

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                BaseApplication.getInstance(), notificationChannelID);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setColor(ContextCompat.getColor(BaseApplication.getInstance(),
                R.color.colorPrimaryDark));
        notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        notificationBuilder.setVibrate(new long[0]);
        notificationBuilder.setContentTitle(BaseApplication.getInstance().
                getString(R.string.notification_reply_title));
        notificationBuilder.setContentText(BaseApplication.getInstance().
                getString(R.string.notification_reply_message));
        notificationBuilder.addAction(replyAction);

        return notificationBuilder.build();
    }

    public static Notification createMediaNotification(NotificationCompat.Action action,
                                                       MediaSessionCompat mediaSession,
                                                       String channelID, int notificationID) {

        Intent intent = new Intent(BaseApplication.getInstance(), MusicPlayerService.class);
        intent.setAction(String.valueOf(STOP));

        PendingIntent pendingIntent = PendingIntent.getService(BaseApplication.getInstance().getApplicationContext(),
                1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(BaseApplication.getInstance(), channelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(BaseApplication.getInstance().getString(R.string.artist_name))
                .setContentText(BaseApplication.getInstance().getString(R.string.song_title))
                .setDeleteIntent(pendingIntent)
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle().
                        setShowActionsInCompactView(1, 2, 3).
                        setMediaSession(mediaSession.getSessionToken()));

        notificationBuilder.addAction(getMediaAction(android.R.drawable.ic_media_previous,
                BaseApplication.getInstance().getString(R.string.previous), PREVIOUS, channelID, notificationID));
        notificationBuilder.addAction(getMediaAction(android.R.drawable.ic_media_rew,
                BaseApplication.getInstance().getString(R.string.rewind), REWIND, channelID, notificationID));
        notificationBuilder.addAction(action);
        notificationBuilder.addAction(getMediaAction(android.R.drawable.ic_media_ff,
                BaseApplication.getInstance().getString(R.string.fast_forward), FAST_FORWARD, channelID, notificationID));
        notificationBuilder.addAction(getMediaAction(android.R.drawable.ic_media_next,
                BaseApplication.getInstance().getString(R.string.next), NEXT, channelID, notificationID));
        return notificationBuilder.build();
    }

    /**
     * Returns the Pending Intent for the Broadcast for Noughat OS and returns the Activity for replying
     *
     * @param notificationId Notification ID
     * @param messageId      Message ID
     * @return
     */
    private static PendingIntent getReplyPendingIntent(int notificationId, int messageId) {
        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = new Intent(BaseApplication.getInstance(), NotificationReceiver.class);
            intent.setAction(REPLY_ACTION);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(NOTIFICATION_ID, notificationId);
            intent.putExtra(MESSAGE_ID, messageId);
            return PendingIntent.getBroadcast(BaseApplication.getInstance(), 0, intent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
        } else {
            Intent mainIntent = new Intent(BaseApplication.getInstance(), MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(BaseApplication.getInstance());
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(mainIntent);
            Intent replyIntent = new Intent(BaseApplication.getInstance(), ReplyActivity.class);
            replyIntent.setAction(REPLY_ACTION);
            replyIntent.putExtra(MESSAGE_ID, messageId);
            replyIntent.putExtra(NOTIFICATION_ID, notificationId);
            stackBuilder.addNextIntent(replyIntent);
            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }
    }

    /**
     * Generate Unique Notification ID based on SHAI Algorithm
     *
     * @return
     */
    public static int getNotificationId() {
        try {
            Random addition = SecureRandom.getInstance(ALGORITHM);
            return addition.nextInt(1000) + 1;
        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG, "" + e.getLocalizedMessage());
        }
        return -1;
    }

    /**
     * Returns the Launch Event which gets triggered when user taps on the Notification
     *
     * @param notificationId Notification
     * @return
     */
    private static PendingIntent getLaunchIntent(int notificationId) {
        Intent intent = new Intent(BaseApplication.getInstance(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(NOTIFICATION_ID, notificationId);
        return PendingIntent.getActivity(BaseApplication.getInstance(), 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
    }

    /**
     * Generate the Notification Action based on the Media Action
     *
     * @param icon                icon to be displayed in notification
     * @param title               title to be displayed
     * @param intentAction        Intented Action like Play/Pause/Stop
     * @param notificationChannel Notification Channel
     * @param notificationID      Notification ID
     * @return
     */
    public static NotificationCompat.Action getMediaAction(int icon, String title,
                                                           int intentAction, String notificationChannel,
                                                           int notificationID) {

        Intent intent = new Intent(BaseApplication.getInstance().getApplicationContext(), MusicPlayerService.class);
        intent.setAction(String.valueOf(intentAction));
        intent.putExtra(Utils.NOTIFICATION_ID, notificationID);
        intent.putExtra(Utils.NOTIFICATION_CHANNEL, notificationChannel);
        PendingIntent pendingIntent = PendingIntent.getService(BaseApplication.getInstance().getApplicationContext(),
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Action.Builder(icon, title, pendingIntent).build();
    }

    /**
     * Ready the message from the Intent
     *
     * @param intent Intent object
     * @return
     */
    public static CharSequence getReplyMessage(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(Utils.REPLY);
        }
        return null;
    }


    /**
     * Create the Action Notification Channel for Action Notifiation
     *
     * @param notificationChannelID Notification Channel ID
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static NotificationChannel createActionNotificationChannel(NotificationManager notificationManager,
                                                                      String notificationChannelID) {
        NotificationChannel notificationChannel;
        if (notificationManager.getNotificationChannel(notificationChannelID) == null) {
            notificationChannel = new NotificationChannel(notificationChannelID,
                    CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(NOTIFICATION_CHANNEL_DESCRIPTION);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
        } else {
            notificationChannel = notificationManager.getNotificationChannel(notificationChannelID);
        }
        return notificationChannel;
    }

    /**
     * Creates the Over the Top Notification Channel
     *
     * @param notificationChannelID Notification Channel ID
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static NotificationChannel createOverTheTopNotificationChannel(NotificationManager notificationManager,
                                                                          String notificationChannelID) {
        NotificationChannel notificationChannel;
        if (notificationManager.getNotificationChannel(notificationChannelID) == null) {
            notificationChannel = new NotificationChannel(notificationChannelID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            // Configure the notification channel.
            notificationChannel.setDescription(NOTIFICATION_CHANNEL_DESCRIPTION);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
        } else {
            notificationChannel = notificationManager.getNotificationChannel(notificationChannelID);
        }
        return notificationChannel;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public static NotificationChannel createMediaChannel(NotificationManager notificationManager, String channelID) {
        if (notificationManager.getNotificationChannel(channelID) == null) {
            CharSequence name = "MediaSession";
            String description = "MediaSession and MediaPlayer";
            NotificationChannel notificationChannel = new NotificationChannel(channelID, name, NotificationManager.IMPORTANCE_LOW);
            // Configure the notification channel.
            notificationChannel.setDescription(description);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            return notificationChannel;
        } else {
            return notificationManager.getNotificationChannel(channelID);
        }
    }

    private static NotificationCompat.MessagingStyle getMessagingStyle() {
        Person user = new Person.Builder().setName("Lisha").build();
        Person currentUser = new Person.Builder().setName("Sarabjit").build();
        NotificationCompat.MessagingStyle.Message message1 = new NotificationCompat.MessagingStyle.
                Message("Hi", 0, user);
        NotificationCompat.MessagingStyle.Message message3 = new NotificationCompat.MessagingStyle.
                Message("Where are you ?", 2, user);
        NotificationCompat.MessagingStyle.Message message2 = new NotificationCompat.MessagingStyle.
                Message("Hello !", 1, currentUser);

        return new NotificationCompat.MessagingStyle(user).
                addMessage(message1).
                addMessage(message2).
                addMessage(message3).
                setGroupConversation(true).
                setConversationTitle("Friends");
    }

}
