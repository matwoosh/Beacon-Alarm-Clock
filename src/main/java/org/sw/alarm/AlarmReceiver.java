package org.sw.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.sw.Actions;
import org.sw.App;

import trikita.jedux.Action;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        App.dispatch(new Action<>(Actions.Alarm.WAKEUP));
    }
}
