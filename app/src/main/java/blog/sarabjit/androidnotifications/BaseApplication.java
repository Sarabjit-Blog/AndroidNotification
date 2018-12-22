package blog.sarabjit.androidnotifications;

import android.app.Application;

/**
 * Created by sarbagga on 05/10/18.
 */

public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication=this;
    }

    public static BaseApplication getInstance() {
        return mBaseApplication;
    }
}
