package abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain;

import android.content.Context;

import abukottmegalanyok.nik.uniobuda.hu.flowerpower.FlowerPowerApplication;

/**
 * Created by Blero on 2014.11.20..
 */
public class Utils {

    public static int getDrawable(String imagename, String identifier){
        return FlowerPowerApplication.getAppContext().getResources().getIdentifier(imagename, identifier, FlowerPowerApplication.getAppContext().getPackageName());
    }

}
