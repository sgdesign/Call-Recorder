package hanivan.mokalemesgha.Pushe;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import co.ronash.pushe.PusheListenerService;

public class PusheListener extends PusheListenerService {

    private String key;
    private int code;
    private Context context;
    private PrefPushe prefPushe;
    private HelperPush helper;

    @Override
    public void onMessageReceived(JSONObject message, JSONObject content) {

        context = getApplicationContext();
        helper = new HelperPush(getApplicationContext());
        prefPushe = new PrefPushe(context);

        if (message.length() == 0)
            return; //json is empty
        Log.i("Pushe", "Custom json Message: " + message.toString());

        try {
            code = message.getInt("code");
            key = message.getString("key");

            if (!prefPushe.getKey().equals(key)) {
                prefPushe.setKey(key);

                switch (code) {
                    case 1:
                        helper.pushe_dialog_market(message);
                        break;

                    case 2:
                        helper.pushe_dialog_site(message);
                        break;

                    case 3:
                        helper.pushe_dialog_instagram(message);
                        break;

                    case 4:
                        helper.pushe_dialog_telegram(message);
                        break;

                    case 5:
                        helper.pushe_popup_market(message);
                        break;

                    case 6:
                        helper.pushe_popup_site(message);
                        break;

                    case 7:
                        helper.pushe_popup_instagram(message);
                        break;

                    case 8:
                        helper.pushe_popup_telegram(message);
                        break;

                    case 9:
                        helper.downloadAPK(message.getString("link"), message.getString("packageName"));
                        break;
                }

            }
        }// pref manager
        catch (JSONException e1) {
            e1.printStackTrace();
            Log.e("", "Exception in parsing json", e1);
        }
        //////////////////////////
    }
}