package abukottmegalanyok.nik.uniobuda.hu.flowerpower;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by TC on 2014.11.17..
 */
public class Flower extends View {
    /*
        state = 0 --> friss hajtás
        state = 1 --> növekedés - 1 szirom
        ...
        state = 5 --> növekedés - 5 szirom (MAX méret)
        state = 6 --> pusztulás - 4 szirom (1 már leesett)
        ...
        state = 9 --> pusztulás - 1 szirom
        state = 10 --> elpusztult

        locsolással a pusztulás megállítható, visszafordítható

        Állapotátmenet:
        a) normál működés:  1 --> 2 --> 3... 5 (ha pusztul akkor tovább 6-10-ig, amennyiben a pusztulás során locsolják vissza 1 állapotot)
        b) növekedés közben nem locoslják:
            párok:  1 --> 10
                    2 --> 9
                    3 --> 8
                    4 --> 7
                    5 --> 6
     */

    private static final int STATE_INIT = 0;
    private static final int STATE_L1 = 1;
    private static final int STATE_L2 = 2;
    private static final int STATE_L3 = 3;
    private static final int STATE_L4 = 4;
    private static final int STATE_L5 = 5;
    private static final int STATE_L6 = 6;
    private static final int STATE_L7 = 7;
    private static final int STATE_L8 = 8;
    private static final int STATE_L9 = 9;
    private static final int STATE_L10 = 10;

    private int state;


    public Flower(Context context) {
        super(context);
        init();
    }

    public Flower(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Flower(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public int getState() {
        return state;
    }

    //játékmenet során történő állapotváltoztatás
    public void setState(int state) {
        this.state = state;
        modifyBackground(state);
    }

    // kezdő állapot és kép beállítása
    private void init() {
        this.state = STATE_INIT;
        //this.setBackground();  //0. állapotnak megfelelő kép berakása
    }

    //a játékmenet során történő képcsere
    private void modifyBackground(int state) {
        switch (state) {
            case STATE_L1:
                //this.setBackground(); //1 szirom
                break;
            case STATE_L2:
                //this.setBackground(); //2 szirom
                break;
            case STATE_L3:
                //this.setBackground(); //3 szirom
                break;
            case STATE_L4:
                //this.setBackground(); //4 szirom
                break;
            case STATE_L5:
                //this.setBackground(); //5 szirom
                break;
            case STATE_L6:
                //this.setBackground(); //4 szirom
                break;
            case STATE_L7:
                //this.setBackground(); //3 szirom
                break;
            case STATE_L8:
                //this.setBackground(); //2 szirom
                break;
            case STATE_L9:
                //this.setBackground(); //1 szirom
                break;
            case STATE_L10:
                //this.setBackground(); //kimúlt
                break;
        }
    }

}
