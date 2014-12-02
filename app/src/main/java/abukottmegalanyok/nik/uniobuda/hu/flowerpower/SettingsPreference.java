package abukottmegalanyok.nik.uniobuda.hu.flowerpower;

import android.content.Context;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.preference.PreferenceActivity;
import android.util.AttributeSet;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Blero on 2014.10.25..
 */
public class SettingsPreference extends DialogPreference {

    public SettingsPreference(Context context, AttributeSet attrs) {
        super(context, attrs);


        setDialogLayoutResource(R.layout.number_picker_dialog);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);

        setDialogIcon(null);
    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
    */

}
