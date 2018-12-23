package blog.sarabjit.androidnotifications;

import android.app.Application;

/**
 * Created by sarabjit Bagga on 05/10/18.
 */

public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication;

    public static BaseApplication getInstance() {
        return mBaseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication=this;
    }
}
