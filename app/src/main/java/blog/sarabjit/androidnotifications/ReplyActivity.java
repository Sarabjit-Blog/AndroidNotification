package blog.sarabjit.androidnotifications;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import blog.sarabjit.androidnotifications.utils.Utils;

/**
 * Created by sarabjit Bagga on 28/12/18.
 */

public class ReplyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_reply);
        if (getIntent() != null && getIntent().getAction() != null && getIntent().getAction().equalsIgnoreCase(Utils.REPLY_ACTION)) {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                int notificationId = getIntent().getIntExtra("notificationId", 0);
                manager.cancel(notificationId);
            }
        }
    }
}
