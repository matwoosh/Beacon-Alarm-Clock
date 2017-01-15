package org.sw.estimote;

import android.util.Log;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.eddystone.Eddystone;

import org.sw.alarm.TemperatureActivity;

/**
 * Created by Mateusz on 15/01/2017.
 */

public class BeaconTemperatureManager {

    private static final String TAG = BeaconTemperatureManager.class.getSimpleName();

    private BeaconManager beaconManager;

    private String scanId;

    public BeaconTemperatureManager(TemperatureActivity temperatureActivity) {
        beaconManager = new BeaconManager(temperatureActivity);
        beaconManager.setEddystoneListener(eddystones -> {
            Log.d(TAG, "Nearby Eddystone beacons: " + eddystones);
            for (Eddystone e : eddystones) {
                if (e.telemetry != null) {
                    temperatureActivity.showTemperature(String.valueOf(e.telemetry.temperature));
                }
            }
        });
    }

    public void startMonitoring() {
        beaconManager.connect(() -> scanId = beaconManager.startEddystoneScanning());
    }

    public void stopMonitoring() {
        beaconManager.stopEddystoneScanning(scanId);
        beaconManager.disconnect();
    }
}
