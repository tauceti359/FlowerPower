package abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * Created by Blero on 2014.11.20..
 */
public class ClickOccupier {

    public static int DEFAULT_WAIT_MS = 2000;
    private static Date occupiedUntil = null;

    public static boolean occupy(int waitMs){
        if(occupiedUntil != null && occupiedUntil.after(new Date())){
            return false;
        }
        occupiedUntil = DateUtils.addMilliseconds(new Date(), DEFAULT_WAIT_MS);
        return true;
    }

    public static boolean occupy(){
        return occupy(DEFAULT_WAIT_MS);
    }

}
