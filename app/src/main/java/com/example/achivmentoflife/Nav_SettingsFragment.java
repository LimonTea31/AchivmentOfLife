package com.example.achivmentoflife;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class Nav_SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}