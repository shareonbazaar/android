package eu.shareonbazaar.dev.bazaar.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private static final String PREF_NAME = "PREF";
    private SharedPreferences sharedPref;

    public SharedPreference(Context context) {
        sharedPref = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
    }

    public boolean isFirstTime(String key){
        String firstTimeString = sharedPref.getString(key, null);
        return firstTimeString == null;
    }

    public void saveToken(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String retrieveToken(String key) {
        return sharedPref.getString(key, null);
    }
}
