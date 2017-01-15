package org.sw.estimote;

import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.eddystone.Eddystone;

import java.util.ArrayList;
import java.util.List;

import org.sw.alarm.AlarmActivity;

public class BeaconAlarmManager {

    private static final String TAG = BeaconAlarmManager.class.getSimpleName();

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
        beaconManager.connect(() -> {
            for (Region region : regionsToMonitor) {
                beaconManager.startMonitoring(region);
            }
        });
    }

    public void stopMonitoring() {
        beaconManager.connect(() -> {
            for (Region region : regionsToMonitor) {
                beaconManager.stopMonitoring(region);
            }
        });
    }

}
