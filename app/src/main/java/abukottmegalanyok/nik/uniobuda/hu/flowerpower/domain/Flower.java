package abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by TC_L on 2014.12.05..
 */
public class Flower extends ImageView {

    private int level;
//    private int humidity;
    public static final String flowerImageResource = "tr_{szam}";

    public Flower(Context context) {
        super(context);
        init();
    }

    public Flower(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Flower(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        this.level = 0;
//        this.humidity = 10;
        Refresh();
    }

    //create the picture name from flower level and static string flowerImageResource
    public String getImageResourceName(){
        String temp = flowerImageResource;
        String temp2 = temp.replace("{szam}", Integer.toString(this.getLevel()));
        return temp2;
    }

    //increase the flower's level
    public void LevelUp(){
        if (this.level < 5)
            this.level = this.getLevel() + 1;
    }

    //decrease the flower's level
    public void LevelDown(){
        this.level = this.getLevel() - 1;
    }

    //set the flower's humidity to max value (10)
//    public void HumidityUpToMax(){
//        this.humidity = 10;
//    }

    //decrease the flower's humidity value
//    public void HumidityDown(){
//        this.humidity--;
//    }


    //set the flower's image based on the level
    public void Refresh(){
       this.setImageResource(Utils.getDrawable(getImageResourceName(), "drawable"));
    }

//    public int GetBackgroundImageId(){
//        return Utils.getDrawable(getImageResourceName(), "drawable");
//    }


    public int getLevel() {
        return level;
    }

//    public void setLevel(int value) { this.level = value; }


}
