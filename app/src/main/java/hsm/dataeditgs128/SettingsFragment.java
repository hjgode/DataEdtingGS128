package hsm.dataeditgs128;


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

        mPreferences=this.getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
/*
        //load
        SharedPreferences prefs = getActivity().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Boolean currentMode = prefs.getBoolean(PREF_KEY_ENABLE, true);
*/
    }

    @Override
    public void onResume(){
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    public static SettingsFragment newInstance() {
        Log.d(TAG, "SettingsFragment newInstance");
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
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
