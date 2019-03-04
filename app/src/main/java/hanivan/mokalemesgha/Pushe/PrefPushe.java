package hanivan.mokalemesgha.Pushe;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefPushe {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "push";
    private static final String PREF_KEY = "key";

    public PrefPushe(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /////////////////////////////////////////////

    public void setKey(String key) {
        editor.putString(PREF_KEY, key);
        editor.commit();
    }

    public String getKey() {
        return pref.getString(PREF_KEY, "1234567899876JKGJG543210");
    }

}