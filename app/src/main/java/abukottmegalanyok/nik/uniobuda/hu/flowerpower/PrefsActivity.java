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
        //prefs-ben lévő, key kulcsú beállítás megváltozásának lekezelése

        //Init
        SharedPreferences sp = sharedPreferences;
        String key = s;
        int defValue = 1;
        //PutInt
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, sp.getInt(key, defValue));

        editor.commit();

        //Toast
        String toastOut = sp.getString("listpref", "Default");
        Toast.makeText(this, toastOut, Toast.LENGTH_SHORT).show();

        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //String toastOut = sp.getString("listpref", "Default");
        //Toast.makeText(this, toastOut, Toast.LENGTH_SHORT).show();

    }
}