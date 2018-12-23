package blog.sarabjit.androidnotifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.widget.Toast;

import blog.sarabjit.androidnotifications.utils.Utils;

/**
 * Created by sarabjit Bagga on 30/12/18.
 */

public class MusicPlayerService extends Service {
    private static final String TAG = "MusicService.class";
    private MediaPlayer mMediaPlayer;
    private MediaSessionManager mManager;
    private MediaSessionCompat mSession;
    private MediaControllerCompat mController;
    private int mNotificationID;
    private String strNotificationChannel;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mManager == null) {
            initializeSession();
        }
        handleIntent(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void handleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null)
            return;

        int action = Integer.parseInt(intent.getAction());
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mNotificationID = bundle.getInt(Utils.NOTIFICATION_ID, 0);
            strNotificationChannel = bundle.getString(Utils.NOTIFICATION_CHANNEL);
        }
        handleIntentAction(action);
    }

    /**
     * Handle Intent action
     *
     * @param action
     */
    private void handleIntentAction(int action) {
        switch (action) {
            case Utils.PLAY:
                mController.getTransportControls().play();
                break;
            case Utils.PAUSE:
                mController.getTransportControls().pause();
                break;
            case Utils.FAST_FORWARD:
                mController.getTransportControls().fastForward();
                break;
            case Utils.REWIND:
                mController.getTransportControls().rewind();
                break;
            case Utils.PREVIOUS:
                mController.getTransportControls().skipToPrevious();
                break;
            case Utils.NEXT:
                mController.getTransportControls().skipToNext();
                break;
            case Utils.STOP:
                mController.getTransportControls().stop();
                break;
            default:
                break;
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initializeSession() {
        mMediaPlayer = new MediaPlayer();
        mSession = new MediaSessionCompat(this, TAG);
        mSession.setMetadata(new MediaMetadataCompat.Builder()
                .build());
        mSession.setActive(true);//Indicating you are ready to take inputs

        try {
            mController = new MediaControllerCompat(getApplicationContext(), mSession.getSessionToken());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        mSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                super.onPlay();
                Log.e(TAG, getString(R.string.play));
                createChannel();
                showNotification(Utils.createMediaNotification(Utils.getMediaAction(
                        android.R.drawable.ic_media_pause, getString(R.string.pause), Utils.PAUSE, strNotificationChannel, mNotificationID),
                        mSession, strNotificationChannel, mNotificationID));
            }

            @Override
            public void onPause() {
                super.onPause();
                Log.e(TAG, getString(R.string.pause));
                createChannel();
                NotificationCompat.Action action = Utils.getMediaAction(android.R.drawable.ic_media_play,
                        getString(R.string.play), Utils.PLAY, strNotificationChannel, mNotificationID);
                Notification notification = Utils.createMediaNotification(action, mSession, strNotificationChannel, mNotificationID);
                showNotification(notification);
            }

            @Override
            public void onSkipToNext() {
                super.onSkipToNext();
                Log.e(TAG, getString(R.string.next));
                //Change media here
                createChannel();
                showNotification(Utils.createMediaNotification(Utils.getMediaAction(android.R.drawable.ic_media_pause,
                        getString(R.string.pause), Utils.PAUSE, strNotificationChannel, mNotificationID), mSession, strNotificationChannel, mNotificationID));
                makeToast(getString(R.string.play_next));
            }

            @Override
            public void onSkipToPrevious() {
                super.onSkipToPrevious();
                Log.e(TAG, getString(R.string.previous));
                createChannel();
                makeToast(getString(R.string.play_previous));
                showNotification(Utils.createMediaNotification(Utils.getMediaAction(android.R.drawable.ic_media_pause,
                        getString(R.string.pause), Utils.PAUSE, strNotificationChannel, mNotificationID), mSession, strNotificationChannel, mNotificationID));
            }

            @Override
            public void onFastForward() {
                super.onFastForward();
                Log.e(TAG, getString(R.string.fast_forward));
                makeToast(getString(R.string.fast_forward));
            }

            @Override
            public void onRewind() {
                super.onRewind();
                Log.e(TAG, getString(R.string.rewind));
                makeToast(getString(R.string.rewind));
            }

            @Override
            public void onStop() {
                Log.e(TAG, getString(R.string.stop));
                NotificationManager notificationManager = (NotificationManager) getApplicationContext().
                        getSystemService(Context.NOTIFICATION_SERVICE);
                if (notificationManager != null) {
                    notificationManager.cancel(mNotificationID);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && strNotificationChannel != null) {
                        notificationManager.deleteNotificationChannel(strNotificationChannel);
                    }
                }
                Intent intent = new Intent(getApplicationContext(), MusicPlayerService.class);
                stopService(intent);
                super.onStop();
            }

            @Override
            public void onSeekTo(long pos) {
                super.onSeekTo(pos);
                Log.e(TAG, getString(R.string.seek));
                makeToast(getString(R.string.seek));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onUnbind(Intent intent) {
        mSession.release();
        return super.onUnbind(intent);
    }

    /**
     * Used to update Notification
     *
     * @param notification Notification Object
     */
    private void showNotification(Notification notification) {
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) this.
                getSystemService(NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(mNotificationID, notification);
        }
    }

    /**
     * Creates a channel For Notification
     */
    private void createChannel() {
        NotificationChannel notificationChannel = null;
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().
                getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = Utils.createMediaChannel(notificationManager, strNotificationChannel);
        }

        if (notificationManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationChannel != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /**
     * Displays Notification
     *
     * @param label text to be displayed
     */
    private void makeToast(String label) {
        Toast.makeText(getApplicationContext(), label, Toast.LENGTH_SHORT).show();
    }
}
