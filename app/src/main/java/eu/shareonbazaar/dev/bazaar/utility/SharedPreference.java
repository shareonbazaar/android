package eu.shareonbazaar.dev.bazaar.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Larry on 10/8/2016.
 */
public class SharedPreference {
    private static final String PREF_NAME = "PREF";
    SharedPreferences sharedPref;

    public SharedPreference(Context context) {
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String retrieveToken(String key) {
        String result = sharedPref.getString(key, "");

        return result;
    }
}
