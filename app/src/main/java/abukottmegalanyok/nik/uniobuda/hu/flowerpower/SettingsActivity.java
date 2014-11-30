package abukottmegalanyok.nik.uniobuda.hu.flowerpower;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Blero on 2014.10.25..
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
