package com.example.liqingliu.learnprefernceactivity;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by liqingliu on 17/11/2.
 */

public class MyPreferenceActivity extends PreferenceActivity {

    PreferenceManager preferenceManager;
    CheckBoxPreference checkBoxPreference;
    ListPreference listPreference;
    EditTextPreference editTextPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.mypreferce);
        preferenceManager = getPreferenceManager();
        checkBoxPreference =(CheckBoxPreference) preferenceManager.findPreference("checkbox");
        Toast.makeText(this,"当前状态: "+checkBoxPreference.isChecked(),Toast.LENGTH_SHORT).show();

        listPreference = (ListPreference) preferenceManager.findPreference("list");
        Toast.makeText(this,"当前状态: "+listPreference.getEntry() + " | " + listPreference.getValue(),Toast.LENGTH_SHORT).show();

        editTextPreference = (EditTextPreference)preferenceManager.findPreference("text");
        Toast.makeText(this,"当前状态: "+ editTextPreference.getText(),Toast.LENGTH_SHORT).show();


    }
}
