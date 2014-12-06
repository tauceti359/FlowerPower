package abukottmegalanyok.nik.uniobuda.hu.flowerpower;

import android.app.Application;
import android.content.Context;

import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.Flower;

/**
 * Created by Blero on 2014.11.17..
 */
public class FlowerPowerApplication extends Application {

    private static Context context;
    private int flowerLevel;

    public static Context getAppContext()
    {
        return FlowerPowerApplication.context;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        FlowerPowerApplication.context=this;
    }


    public int getFlowerLevel() {
        return flowerLevel;
    }

    public void setFlowerLevel(int flowerLevel) {
        this.flowerLevel = flowerLevel;
    }
}
