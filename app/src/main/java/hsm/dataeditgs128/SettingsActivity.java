package hsm.dataeditgs128;

import android.app.Activity;
import android.app.Fragment;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;

public class SettingsActivity extends AppCompatActivity {

    String TAG="SettingsActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //addPreferencesFromResource(R.xml.preferences);
        Log.d(TAG,"SettingsActivity onCreate");
        hsm.dataeditgs128.SettingsFragment settingsFragment=new hsm.dataeditgs128.SettingsFragment().newInstance();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(android.R.id.content, new hsm.dataeditgs128.SettingsFragment().newInstance())
                .commit();
    }


}
