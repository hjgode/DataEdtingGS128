package hsm.dataeditgs128;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import static android.content.Context.MODE_MULTI_PROCESS;
import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements Common, SharedPreferences.OnSharedPreferenceChangeListener {


    static String TAG=Consts.TAG;
    private SharedPreferences mPreferences;

    public SettingsFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        Log.d(TAG, "SettingsFragment onCreatePreferences");
        setPreferencesFromResource(R.xml.preferences, rootKey);

        //mPreferences=this.getActivity().getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
        mPreferences= getPreferenceScreen().getSharedPreferences(); // this gives hsm.dataeditgs128_preferences.xml
        //mPreferences=PreferenceManager.getDefaultSharedPreferences(getActivity());

        mPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "SettingsFragment onResume");
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "SettingsFragment onPause");
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    public static SettingsFragment newInstance() {
        Log.d(TAG, "SettingsFragment newInstance");
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(Consts.TAG, "onSharedPreferenceChanged: sharedPrefs=" + sharedPreferences + ", key="+key);
        if(key.equals(PREF_KEY_ENABLE)){
            boolean bEnabled=sharedPreferences.getBoolean(PREF_KEY_ENABLE, false);
            Log.d(Consts.TAG, "PREF_KEY_ENABLE now="+bEnabled);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean(PREF_KEY_ENABLE, bEnabled);
            editor.commit();
        }
/*
        android.support.v7.preference.Preference pref = findPreference(key);

        if (pref instanceof android.support.v7.preference.EditTextPreference) {
            android.support.v7.preference.EditTextPreference editPref = (android.support.v7.preference.EditTextPreference) pref;

            //pref.setSummary(editPref.getSummary());
//            this.findPreference(key).setSummary(editPref.getSummary());

            String gs1replace = mPreferences.getString(PREF_KEY_GS1REPLACE, "#");
            this.findPreference(PREF_KEY_GS1REPLACE).setSummary(gs1replace);

        }
*/
        return ;
    }
}
