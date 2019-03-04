package hanivan.mokalemesgha.base;

import android.content.Context;
import android.view.MenuItem;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivityUpEnable extends BaseActivity2 {
    private final int actionBarTitleId;

    public BaseActivityUpEnable(final int actionBarTitleId) {
        this.actionBarTitleId = actionBarTitleId;
    }

    // font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
	
    @Override
    protected void onStart() {
        super.onStart();
   }
}
