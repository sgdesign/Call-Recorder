package hanivan.mokalemesgha.Pushe;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hanivan.mokalemesgha.R;

import java.io.InputStream;

public class DialogPusheActivity extends Activity {
    TextView txt_title, txt_description;
    Button btn_close, btn_download;
    private String _title, _description, _logo, _banner, _link, _btn_text;
    private int _code;
    private Typeface font;
    private Context context;
    private Intent i;
    private Bundle b;
    private ImageView im_logo, im_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeStatusBar();
        setContentView(R.layout.activity_dialog_pushe);
        context = DialogPusheActivity.this;

        font = Typeface.createFromAsset(getAssets(), "fonts/iransans.ttf");

        txt_title = (TextView) findViewById(R.id.txt_pushe_title);
        txt_description = (TextView) findViewById(R.id.txt_pushe_description);
        btn_close = (Button) findViewById(R.id.btn_pushe_close);
        btn_download = (Button) findViewById(R.id.btn_pushe_download);
        im_logo = (ImageView) findViewById(R.id.im_pushe_logo);
        im_banner = (ImageView) findViewById(R.id.im_pushe_banner);

        txt_description.setMovementMethod(new ScrollingMovementMethod());

        txt_title.setTypeface(font);
        txt_description.setTypeface(font);
        btn_close.setTypeface(font);
        btn_download.setTypeface(font);

        i = getIntent();
        b = i.getExtras();

        _code = b.getInt("code");
        _title = b.getString("title");
        _description = b.getString("description");
        _logo = b.getString("logo");
        _banner = b.getString("banner");
        _link = b.getString("link");
        _btn_text = b.getString("btn_text");

        if (_code == 7) {

            try {
                Log.e("topsaze 2 ", b.toString());
                instagram();
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }

            finish();
            System.exit(0);
        }


        init_item();

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    switch (_code) {

                        case 1:
                            market_site();
                            break;

                        case 2:
                            market_site();
                            break;

                        case 3:
                            instagram();
                            break;

                        case 4:
                            telegram();
                            break;
                    }

                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void market_site() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(_link)));
        finish();
        //System.exit(0);
    }

    private void instagram() {

        Intent likeIng = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/" + b.getString("link")));
        likeIng.setPackage("com.instagram.android");
        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/" + b.getString("link"))));
        }
        finish();
        System.exit(0);

    }

    private void telegram() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/" + b.getString("link"))));
        finish();
        System.exit(0);

    }

    private void init_item() {

        try {
            txt_title.setText(_title);
            txt_description.setText(_description);
            txt_description.setMovementMethod(new ScrollingMovementMethod());
            btn_download.setText(_btn_text);

            new DownloadImageTask(im_logo).execute(_logo);
            new DownloadImageTask(im_banner).execute(_banner);
        } catch (Exception e) {
        }

        // this title and description

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public void onBackPressed() {

    }

    private void removeStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
