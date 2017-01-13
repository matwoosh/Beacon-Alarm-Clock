package org.sw.alarm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import trikita.anvil.Anvil;
import trikita.anvil.RenderableView;

import static trikita.anvil.BaseDSL.text;
import static trikita.anvil.DSL.*;

import trikita.jedux.Action;
import org.sw.Actions;
import org.sw.App;
import org.sw.R;
import org.sw.estimote.BeaconID;
import org.sw.estimote.BeaconAlarmManager;
import org.sw.ui.ActiveAlarmLayout;
import org.sw.ui.AlarmLayout;
import org.sw.ui.Theme;


public class AlarmActivity extends Activity {
    private PowerManager.WakeLock mWakeLock;
    private BeaconAlarmManager beaconAlarmManager;


    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "AlarmActivity");
        mWakeLock.acquire();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        // fill status bar with a theme dark color on post-Lollipop devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Theme.get(App.getState().settings().theme()).primaryDarkColor);
        }

        setContentView(new RenderableView(this) {
            public void view() {
                onClick(v -> stopAlarm());
                ActiveAlarmLayout.view();
            }
        });

        startBeaconAlarmManager();
    }

    private void startBeaconAlarmManager() {
        beaconAlarmManager = new BeaconAlarmManager(this);
        beaconAlarmManager.addBeacon(new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 55175, 50165));
        beaconAlarmManager.startMonitoring();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWakeLock.release();
    }

    public void stopAlarm() {
        beaconAlarmManager.stopMonitoring();
        App.dispatch(new Action<>(Actions.Alarm.DISMISS));

        Intent i = new Intent(this, TemperatureActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(i);

        finish();
    }
}
