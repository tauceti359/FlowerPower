package abukottmegalanyok.nik.uniobuda.hu.flowerpower;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class PrefsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        settings.registerOnSharedPreferenceChangeListener( this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        //Toast
        SharedPreferences sp = sharedPreferences;
        String toastOut = sp.getString("listpref", "Default");
        Toast.makeText(this, "Az öntözési ciklus mostantól: " + toastOut + " óra!", Toast.LENGTH_LONG).show();
    }
}