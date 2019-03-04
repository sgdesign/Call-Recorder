package hanivan.mokalemesgha.base;

import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivityUpEnableWithMenu extends BaseActivityUpEnable {
    private int menuId;

    // font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public BaseActivityUpEnableWithMenu(int actionBarTitleId, int menuId) {
        super(actionBarTitleId);
       this.menuId = menuId;
    }
}
