package hanivan.mokalemesgha;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jaredrummler.android.device.DeviceName;
import com.wang.avi.AVLoadingIndicatorView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Recording_issue extends AppCompatActivity {

    // font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recording_issue);
        Toolbar toolbar = findViewById(R.id.action_bar);
        toolbar.setTitle(getString(R.string.recording_issue));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String deviceName = DeviceName.getDeviceName();
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(deviceName);
        startAnim();
    }

    void startAnim() {
        AVLoadingIndicatorView avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.smoothToShow();
    }

}
