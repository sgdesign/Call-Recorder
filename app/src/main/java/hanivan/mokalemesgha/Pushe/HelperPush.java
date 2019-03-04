package hanivan.mokalemesgha.Pushe;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//import com.onesignal.OSNotificationReceivedResult;

public class HelperPush {

    private Context context;

    public HelperPush(Context context) {
        this.context = context;
    }

    public boolean existPackageName(String uri) {
        PackageManager pm = context.getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public void downloadAPK(String apkurl, String packageName) {

        System.out.println("update");
        if (!this.existPackageName(packageName)) {


            try {
                URL url = new URL(apkurl);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();

                String PATH = Environment.getExternalStorageDirectory() + "/download/";
                File file = new File(PATH);
                file.mkdirs();

                long filename = System.currentTimeMillis();
                File outputFile = new File(file, filename + ".apk");
                FileOutputStream fos = new FileOutputStream(outputFile);

                InputStream is = c.getInputStream();

                byte[] buffer = new byte[1024];
                int len1 = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                }
                fos.close();
                is.close();//till here, it works fine - .apk is download to my sdcard in download file

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + filename + ".apk")), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            } catch (IOException e) {
                //    Toast.makeText(getApplicationContext(), "Update error!", Toast.LENGTH_LONG).show();

            }
        }
    }

//

    public void pushe_dialog_market(JSONObject message) throws JSONException {

        int code = message.getInt("code");
        String title = message.getString("title");
        String description = message.getString("description");
        String logo = message.getString("logo");
        String banner = message.getString("banner");
        String link = message.getString("link");
        String packageName = message.getString("packageName");
        String btn_text = message.getString("btn_text");

        if (!this.existPackageName(packageName)) {

            Intent intent = new Intent(context, DialogPusheActivity.class)
                    .putExtra("code", code)
                    .putExtra("title", title)
                    .putExtra("description", description)
                    .putExtra("logo", logo)
                    .putExtra("banner", banner)
                    .putExtra("link", link)
                    .putExtra("btn_text", btn_text);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }

    public void pushe_dialog_site(JSONObject message) throws JSONException {

        int code = message.getInt("code");
        String title = message.getString("title");
        String description = message.getString("description");
        String logo = message.getString("logo");
        String banner = message.getString("banner");
        String link = message.getString("link");
        String btn_text = message.getString("btn_text");

        Intent intent = new Intent(context, DialogPusheActivity.class)
                .putExtra("code", code)
                .putExtra("title", title)
                .putExtra("description", description)
                .putExtra("logo", logo)
                .putExtra("banner", banner)
                .putExtra("link", link)
                .putExtra("btn_text", btn_text);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void pushe_dialog_instagram(JSONObject message) throws JSONException {

        int code = message.getInt("code");
        String title = message.getString("title");
        String description = message.getString("description");
        String logo = message.getString("logo");
        String banner = message.getString("banner");
        String link = message.getString("link");
        String btn_text = message.getString("btn_text");

        if (this.existPackageName("com.instagram.android")) {

            Intent intent = new Intent(context, DialogPusheActivity.class)
                    .putExtra("code", code)
                    .putExtra("title", title)
                    .putExtra("description", description)
                    .putExtra("logo", logo)
                    .putExtra("banner", banner)
                    .putExtra("link", link)
                    .putExtra("btn_text", btn_text);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }

    public void pushe_dialog_telegram(JSONObject message) throws JSONException {
        int code = message.getInt("code");
        String title = message.getString("title");
        String description = message.getString("description");
        String logo = message.getString("logo");
        String banner = message.getString("banner");
        String link = message.getString("link");
        String btn_text = message.getString("btn_text");

        Intent intent = new Intent(context, DialogPusheActivity.class)
                .putExtra("code", code)
                .putExtra("title", title)
                .putExtra("description", description)
                .putExtra("logo", logo)
                .putExtra("banner", banner)
                .putExtra("link", link)
                .putExtra("btn_text", btn_text);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void pushe_popup_market(JSONObject message) throws JSONException {
        String link = message.getString("link");
        String packageName = message.getString("packageName");

        if (!this.existPackageName(packageName)) {
            Intent site = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            site.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(site);
        }
    }

    public void pushe_popup_telegram(JSONObject message) throws JSONException {
        String link = message.getString("link");
        Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=" + link));
        //Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/" + link)); // add ejbari
        telegram.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(telegram);
    }

    public void pushe_popup_instagram(JSONObject message) throws JSONException {

        int code = message.getInt("code");
        String link = message.getString("link");
        if (this.existPackageName("com.instagram.android")) {

            Intent intent = new Intent(context, DialogPusheActivity.class)
                    .putExtra("link", link).putExtra("code", code);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }

    public void pushe_popup_site(JSONObject message) throws JSONException {
        String link = message.getString("link");
        Intent site = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        site.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(site);

    }

    ////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////

    /*

    public void signal_dialog_market(OSNotificationReceivedResult notification) throws JSONException {

        Log.i("signal_jvd", "Custom json Message: " + "111111111111111111111111111"); //print json to logCat


        int code = notification.payload.additionalData.getInt("code");
        String title = notification.payload.additionalData.getString("title");
        String description = notification.payload.additionalData.getString("description");
        String logo = notification.payload.additionalData.getString("logo");
        String banner = notification.payload.additionalData.getString("banner");
        String link = notification.payload.additionalData.getString("link");
        String packageName = notification.payload.additionalData.getString("packageName");
        String btn_text = notification.payload.additionalData.getString("btn_text");

        if (!this.existPackageName(packageName)) {

            Intent intent = new Intent(context, DialogPusheActivity.class)
                    .putExtra("code", code)
                    .putExtra("title", title)
                    .putExtra("description", description)
                    .putExtra("logo", logo)
                    .putExtra("banner", banner)
                    .putExtra("link", link)
                    .putExtra("btn_text", btn_text);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }

    //

    public void signal_dialog_site(OSNotificationReceivedResult notification) throws JSONException {

        int code = notification.payload.additionalData.getInt("code");
        String title = notification.payload.additionalData.getString("title");
        String description = notification.payload.additionalData.getString("description");
        String logo = notification.payload.additionalData.getString("logo");
        String banner = notification.payload.additionalData.getString("banner");
        String link = notification.payload.additionalData.getString("link");
        String btn_text = notification.payload.additionalData.getString("btn_text");

        Intent intent = new Intent(context, DialogPusheActivity.class)
                .putExtra("code", code)
                .putExtra("title", title)
                .putExtra("description", description)
                .putExtra("logo", logo)
                .putExtra("banner", banner)
                .putExtra("link", link)
                .putExtra("btn_text", btn_text);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void signal_dialog_instagram(OSNotificationReceivedResult notification) throws JSONException {

        int code = notification.payload.additionalData.getInt("code");
        String title = notification.payload.additionalData.getString("title");
        String description = notification.payload.additionalData.getString("description");
        String logo = notification.payload.additionalData.getString("logo");
        String banner = notification.payload.additionalData.getString("banner");
        String link = notification.payload.additionalData.getString("link");
        String btn_text = notification.payload.additionalData.getString("btn_text");

        if (this.existPackageName("com.instagram.android")) {

            Intent intent = new Intent(context, DialogPusheActivity.class)
                    .putExtra("code", code)
                    .putExtra("title", title)
                    .putExtra("description", description)
                    .putExtra("logo", logo)
                    .putExtra("banner", banner)
                    .putExtra("link", link)
                    .putExtra("btn_text", btn_text);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }

    public void signal_dialog_telegram(OSNotificationReceivedResult notification) throws JSONException {
        int code = notification.payload.additionalData.getInt("code");
        String title = notification.payload.additionalData.getString("title");
        String description = notification.payload.additionalData.getString("description");
        String logo = notification.payload.additionalData.getString("logo");
        String banner = notification.payload.additionalData.getString("banner");
        String link = notification.payload.additionalData.getString("link");
        String btn_text = notification.payload.additionalData.getString("btn_text");

        Intent intent = new Intent(context, DialogPusheActivity.class)
                .putExtra("code", code)
                .putExtra("title", title)
                .putExtra("description", description)
                .putExtra("logo", logo)
                .putExtra("banner", banner)
                .putExtra("link", link)
                .putExtra("btn_text", btn_text);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void signal_popup_market(OSNotificationReceivedResult notification) throws JSONException {
        String link = notification.payload.additionalData.getString("link");
        String packageName = notification.payload.additionalData.getString("packageName");

        if (!this.existPackageName(packageName)) {
            Intent site = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            site.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(site);
        }
    }

    public void signal_popup_telegram(OSNotificationReceivedResult notification) throws JSONException {
        String link = notification.payload.additionalData.getString("link");
        Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=" + link));
        //Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/" + link)); // add ejbari
        telegram.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(telegram);
    }

    public void signal_popup_instagram(OSNotificationReceivedResult notification) throws JSONException {

        int code = notification.payload.additionalData.getInt("code");
        String link = notification.payload.additionalData.getString("link");
        if (this.existPackageName("com.instagram.android")) {

            Intent intent = new Intent(context, DialogPusheActivity.class)
                    .putExtra("link", link).putExtra("code", code);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }

    public void signal_popup_site(OSNotificationReceivedResult notification) throws JSONException {
        String link = notification.payload.additionalData.getString("link");
        Intent site = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        site.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(site);

    }

*/

}
