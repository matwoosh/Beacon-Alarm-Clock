package org.sw.estimote;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sw.alarm.AlarmActivity;

public class BeaconAlarmManager {

    private static final String TAG = "BeaconNotifications";

    private BeaconManager beaconManager;

    private List<Region> regionsToMonitor = new ArrayList<>();


    public BeaconAlarmManager(AlarmActivity alarmActivity) {
        beaconManager = new BeaconManager(alarmActivity);
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {

            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                alarmActivity.stopAlarm();
                Log.d(TAG, "onEnteredRegion: " + region.getIdentifier());

            }

            @Override
            public void onExitedRegion(Region region) {
                Log.d(TAG, "onExitedRegion: " + region.getIdentifier());
            }
        });
    }

    public void addBeacon(BeaconID beaconID) {
        Region region = beaconID.toBeaconRegion();
        regionsToMonitor.add(region);
    }

    public void startMonitoring() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                for (Region region : regionsToMonitor) {
                    beaconManager.startMonitoring(region);
                }
            }
        });
    }

    public void stopMonitoring() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                for (Region region : regionsToMonitor) {
                    beaconManager.stopMonitoring(region);
                }
            }
        });
    }

}
