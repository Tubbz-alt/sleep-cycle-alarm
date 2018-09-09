package com.gmail.brunokawka.poland.sleepcyclealarm;

import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuItem;

import com.gmail.brunokawka.poland.sleepcyclealarm.ui.tabs.accessalarm.alarms.AlarmsFragment;
import com.gmail.brunokawka.poland.sleepcyclealarm.ui.tabs.addalarm.sleepnow.SleepNowFragment;
import com.gmail.brunokawka.poland.sleepcyclealarm.ui.tabs.addalarm.wakeupat.WakeUpAtFragment;
import com.gmail.brunokawka.poland.sleepcyclealarm.utils.ThemeHelper;

public class MainPresenter implements MainContract.MainPresenter {
    private final static String TAG = "MainPresenterLog";

    private MainContract.MainView view;

    public MainPresenter(MainContract.MainView view) {
        this.view = view;
    }

    @Override
    public void handleSetTheme(String changeThemeKey, SharedPreferences sharedPreferences) {
        int themeId = ThemeHelper.getCurrentTheme(changeThemeKey, sharedPreferences);
        view.setAppTheme(themeId);
    }

    @Override
    public void handleBottomNavigationTabClick(MenuItem menuItem) {
        Fragment fragment = null;
        int menuId = menuItem.getItemId();

        switch (menuId) {
            case R.id.action_wakeupat:
                fragment = new WakeUpAtFragment();
                break;
            case R.id.action_alarms:
                fragment = new AlarmsFragment();
                break;
            case R.id.action_sleepnow:
            default:
                fragment = new SleepNowFragment();
                break;
        }

        view.replaceFragment(fragment);
    }

    @Override
    public void handleMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        String itemTitle = item.getTitle().toString();
        view.openMenuActivityWithItemVariables(itemId, itemTitle);
    }

}