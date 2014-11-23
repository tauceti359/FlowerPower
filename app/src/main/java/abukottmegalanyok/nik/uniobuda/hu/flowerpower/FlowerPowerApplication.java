package abukottmegalanyok.nik.uniobuda.hu.flowerpower;

import android.app.Application;
import android.content.Context;

/**
 * Created by Blero on 2014.11.17..
 */
public class FlowerPowerApplication extends Application {

    private static Context context;

    public static Context getAppContext()
    {
        return FlowerPowerApplication.context;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        FlowerPowerApplication.context=this;
    }
}
